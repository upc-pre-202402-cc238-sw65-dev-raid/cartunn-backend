package com.thecoders.cartunnbackend.payment.domain.model.commands;

public record DeleteCartProductsCommand(Long CartId, Long ProductId) {
}
