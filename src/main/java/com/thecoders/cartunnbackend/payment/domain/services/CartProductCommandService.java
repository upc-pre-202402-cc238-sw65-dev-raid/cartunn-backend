package com.thecoders.cartunnbackend.payment.domain.services;

import com.thecoders.cartunnbackend.payment.domain.model.aggregates.Cart;
import com.thecoders.cartunnbackend.payment.domain.model.aggregates.CartProducts;
import com.thecoders.cartunnbackend.payment.domain.model.commands.*;

import java.util.Optional;

public interface CartProductCommandService {
    CartProducts handle(CreateCartProductsCommand command);
    Optional<CartProducts> handle(UpdateCartProductsCommand command);
    void handle(DeleteCartProductsCommand command);
    CartProducts handle(GetCartProductCommand command);
}

