package com.thecoders.cartunnbackend.payment.domain.model.aggregates;

import com.thecoders.cartunnbackend.payment.domain.model.commands.CreateCartCommand;
import com.thecoders.cartunnbackend.payment.domain.model.commands.CreateCartProductsCommand;
import com.thecoders.cartunnbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "cart_product")
public class CartProducts extends AuditableAbstractAggregateRoot<CartProducts> {

    @Column(name = "cart_id", nullable = false)
    Long cartId;
    @Column(name = "product_id", nullable = false)
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
