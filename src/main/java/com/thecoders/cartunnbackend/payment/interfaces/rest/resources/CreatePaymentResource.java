package com.thecoders.cartunnbackend.payment.interfaces.rest.resources;

public record CreatePaymentResource(Long paymentId,
                                    String cardNumber,
                                    String expirationDate,
                                    String cardHolder,
                                    String cvc,
                                    String methodPay) {
}
