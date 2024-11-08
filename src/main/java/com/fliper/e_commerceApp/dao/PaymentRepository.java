package com.fliper.e_commerceApp.dao;

import com.fliper.e_commerceApp.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Additional custom queries can be added here if needed
}
