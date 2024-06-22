package com.thecoders.cartunnbackend.payment.interfaces.rest.resources;

public record UpdatePaymentResource (
                                     String card_number,
                                     String expiration_date,
                                     String card_holder,
                                     String cvc,
                                     String method_pay){
}
