package com.fliper.e_commerceApp.controller;

import com.fliper.e_commerceApp.model.Payment;
import com.fliper.e_commerceApp.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Payment> initiatePayment(@RequestBody Payment payment) {
        Payment initiatedPayment = paymentService.initiatePayment(payment);
        return ResponseEntity.ok(initiatedPayment);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getPaymentStatus(@PathVariable Long paymentId) {
        Payment payment = paymentService.getPaymentById(paymentId);
        if (payment != null) {
            return ResponseEntity.ok(payment);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<Payment> updatePaymentStatus(@PathVariable Long paymentId, @RequestBody Payment payment) {
        Payment updatedPayment = paymentService.updatePaymentStatus(paymentId, payment);
        return ResponseEntity.ok(updatedPayment);
    }
}
