package com.fliper.e_commerceApp.controller;

import com.fliper.e_commerceApp.dao.AuthRequest;
import com.fliper.e_commerceApp.model.UserEntity;
import com.fliper.e_commerceApp.service.UserService;
import com.fliper.e_commerceApp.utill.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserEntity user) {
        try {
            // Check if user already exists by email
            userService.loadUserByEmail(user.getEmail()); // This will throw exception if user is not found
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already registered");
        } catch (UsernameNotFoundException ex) {
            // If user not found (and exception is thrown), save the new user
            userService.saveUser(user);
            return ResponseEntity.ok("User registered successfully");
        }
    }


    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody AuthRequest authRequest) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
            String token = jwtUtil.generateToken(authRequest.getEmail());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
