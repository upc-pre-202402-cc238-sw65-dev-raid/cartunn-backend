package com.thecoders.cartunnbackend.payment.domain.model.aggregates;

import com.thecoders.cartunnbackend.payment.domain.model.commands.CreateCartCommand;
import com.thecoders.cartunnbackend.payment.domain.model.commands.CreateCartProductsCommand;
import com.thecoders.cartunnbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class CartProducts extends AuditableAbstractAggregateRoot<CartProducts> {
    Long cartId;
    Long productId;
    public CartProducts() {
        this.cartId = Long.valueOf(0);
        this.productId = Long.valueOf(0);
    }

    public CartProducts(Long cartId, Long productId) {
        this();
        this.cartId = cartId;
        this.productId = productId;
    }
    public CartProducts(CreateCartProductsCommand command) {
        this();
        this.cartId = command.cartId();
        this.productId = command.productId();
    }
    public CartProducts updateInformation(Long cartId, Long productId) {
        this.cartId = cartId;
        this.productId = productId;
        return this;
    }
}
