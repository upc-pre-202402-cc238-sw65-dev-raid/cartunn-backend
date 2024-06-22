package com.thecoders.cartunnbackend.payment.infrastructure.persistence.jpa.repositories;

import com.thecoders.cartunnbackend.payment.domain.model.aggregates.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Optional<Payment> findByCardHolder(String cardHolder);
    boolean existsByCardHolderAndIdIsNot(String cardHolder, Long id);

    boolean existsByCardHolder(String cardHolder);
}
