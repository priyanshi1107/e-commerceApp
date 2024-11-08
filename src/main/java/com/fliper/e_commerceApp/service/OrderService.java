package com.fliper.e_commerceApp.service;

import com.fliper.e_commerceApp.model.OrderEntity;
import com.fliper.e_commerceApp.dao.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderEntity createOrder(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }

    public Optional<OrderEntity> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public List<OrderEntity> getAllOrdersForUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
