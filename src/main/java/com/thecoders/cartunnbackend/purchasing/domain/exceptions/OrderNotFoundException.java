package com.thecoders.cartunnbackend.purchasing.domain.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long aLong) {
        super("Order with id " + aLong + " not found");
    }
}
