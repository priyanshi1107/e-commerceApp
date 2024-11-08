package com.fliper.e_commerceApp.service;

import com.fliper.e_commerceApp.dao.PaymentRepository;
import com.fliper.e_commerceApp.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // Initiates a new payment
    public Payment initiatePayment(Payment payment) {
        payment.setPaymentStatus("Pending");
        payment.setPaymentDate(new java.util.Date());
        return paymentRepository.save(payment);
    }

    // Retrieves payment details by ID
    public Payment getPaymentById(Long paymentId) {
        Optional<Payment> payment = paymentRepository.findById(paymentId);
        return payment.orElse(null);
    }

    // Updates the status of an existing payment
    public Payment updatePaymentStatus(Long paymentId, Payment payment) {
        Optional<Payment> existingPayment = paymentRepository.findById(paymentId);
        if (existingPayment.isPresent()) {
            Payment updatedPayment = existingPayment.get();
            updatedPayment.setPaymentStatus(payment.getPaymentStatus());
            return paymentRepository.save(updatedPayment);
        }
        return null;
    }
}
