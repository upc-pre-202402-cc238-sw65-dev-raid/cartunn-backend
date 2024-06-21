package com.thecoders.cartunnbackend.payment.interfaces.rest.transform;

import com.thecoders.cartunnbackend.payment.domain.model.commands.UpdateCartCommand;
import com.thecoders.cartunnbackend.payment.domain.model.commands.UpdateCartProductsCommand;
import com.thecoders.cartunnbackend.payment.interfaces.rest.resources.UpdateCartProductResource;
import com.thecoders.cartunnbackend.payment.interfaces.rest.resources.UpdateCartResource;

public class UpdateCartProductCommandFromResourceAssembler {
    public static UpdateCartProductsCommand toCommandFromResource(Long cartId,Long productId) {
        return new UpdateCartProductsCommand(cartId, productId);
    }
}
