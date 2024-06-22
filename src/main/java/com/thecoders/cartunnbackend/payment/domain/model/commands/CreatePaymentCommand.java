package com.thecoders.cartunnbackend.payment.domain.model.commands;

public record CreatePaymentCommand(String card_number,String expiration_date, String card_holder, String cvc, String method_pay) {
}
