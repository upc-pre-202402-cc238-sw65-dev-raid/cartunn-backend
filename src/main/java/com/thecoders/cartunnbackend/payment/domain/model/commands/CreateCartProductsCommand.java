package com.thecoders.cartunnbackend.payment.domain.model.commands;

public record CreateCartProductsCommand(Long cartId, Long productId) {
}
