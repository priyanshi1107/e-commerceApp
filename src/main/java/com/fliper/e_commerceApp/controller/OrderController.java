package com.fliper.e_commerceApp.controller;

import com.fliper.e_commerceApp.model.OrderEntity;
import com.fliper.e_commerceApp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<OrderEntity> createOrder(@RequestBody OrderEntity orderEntity) {
        OrderEntity createdOrderEntity = orderService.createOrder(orderEntity);
        return ResponseEntity.ok(createdOrderEntity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderEntity>> getAllOrdersForUser(@PathVariable Long userId) {
        List<OrderEntity> orderEntities = orderService.getAllOrdersForUser(userId);
        return ResponseEntity.ok(orderEntities);
    }
}
