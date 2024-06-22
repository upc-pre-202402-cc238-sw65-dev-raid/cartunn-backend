package com.thecoders.cartunnbackend.payment.interfaces.rest.resources;

public record PaymentResource(Long id,
                              String card_number,
                              String expiration_date,
                              String card_holder,
                              String cvc,
                              String method_pay) {
}
