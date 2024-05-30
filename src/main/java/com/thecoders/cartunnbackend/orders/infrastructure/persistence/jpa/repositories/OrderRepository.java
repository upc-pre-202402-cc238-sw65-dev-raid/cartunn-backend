package com.thecoders.cartunnbackend.orders.infrastructure.persistence.jpa.repositories;

import com.thecoders.cartunnbackend.orders.domain.model.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
