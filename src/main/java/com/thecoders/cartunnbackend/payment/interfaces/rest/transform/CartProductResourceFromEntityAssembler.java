package com.thecoders.cartunnbackend.payment.interfaces.rest.transform;

import com.thecoders.cartunnbackend.payment.domain.model.aggregates.Cart;
import com.thecoders.cartunnbackend.payment.domain.model.aggregates.CartProducts;
import com.thecoders.cartunnbackend.payment.interfaces.rest.resources.CartProductResource;
import com.thecoders.cartunnbackend.payment.interfaces.rest.resources.CartResource;

public class CartProductResourceFromEntityAssembler {
    public static CartProductResource toResourceFromEntity(CartProducts entity) {
        return new CartProductResource(entity.getCartId(), entity.getProductId());
    }
}
}
