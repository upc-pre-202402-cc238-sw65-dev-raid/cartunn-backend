package com.thecoders.cartunnbackend.payment.interfaces.rest.transform;

import com.thecoders.cartunnbackend.payment.domain.model.commands.CreateCartCommand;
import com.thecoders.cartunnbackend.payment.domain.model.commands.CreateCartProductsCommand;
import com.thecoders.cartunnbackend.payment.interfaces.rest.resources.CreateCartProductResource;
import com.thecoders.cartunnbackend.payment.interfaces.rest.resources.CreateCartResource;

public class CreateCartProductCommandFromResourceAssembler {
    public static CreateCartProductsCommand toCommandFromResource(CreateCartProductResource resource) {
        return new CreateCartProductsCommand(resource.CartId(),resource.ProductId());
    }
}
