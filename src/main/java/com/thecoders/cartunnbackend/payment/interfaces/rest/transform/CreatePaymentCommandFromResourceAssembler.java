package com.thecoders.cartunnbackend.payment.interfaces.rest.transform;


import com.thecoders.cartunnbackend.payment.domain.model.commands.CreatePaymentCommand;
import com.thecoders.cartunnbackend.payment.interfaces.rest.resources.CreatePaymentResource;

public class CreatePaymentCommandFromResourceAssembler {
    public static CreatePaymentCommand toCommandFromResource(CreatePaymentResource resource){
        return new CreatePaymentCommand(
                resource.card_number(),
                resource.expiration_date(),
                resource.card_holder(),
                resource.cvc(),
                resource.method_pay()
        );
    }
}
