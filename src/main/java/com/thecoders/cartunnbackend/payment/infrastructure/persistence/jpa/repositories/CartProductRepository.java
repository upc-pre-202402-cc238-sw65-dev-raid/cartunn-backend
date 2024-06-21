package com.thecoders.cartunnbackend.payment.infrastructure.persistence.jpa.repositories;

import com.thecoders.cartunnbackend.payment.domain.model.aggregates.Cart;
import com.thecoders.cartunnbackend.payment.domain.model.aggregates.CartProducts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartProductRepository extends JpaRepository<CartProducts, Long> {
    List<CartProducts> findByCartId(Long cartId);
    boolean existsByCartId(Long cartId);
    CartProducts findByCartIdProductId(Long cartId, Long productId);

}
