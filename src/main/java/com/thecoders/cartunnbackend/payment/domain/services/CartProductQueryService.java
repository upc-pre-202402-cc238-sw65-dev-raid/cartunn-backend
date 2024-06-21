package com.thecoders.cartunnbackend.payment.domain.services;

import com.thecoders.cartunnbackend.payment.domain.model.aggregates.Cart;
import com.thecoders.cartunnbackend.payment.domain.model.aggregates.CartProducts;
import com.thecoders.cartunnbackend.payment.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface CartProductQueryService {
    List<CartProducts> handle(GetCartProductsByCartIdQuery query);
    List<CartProducts> handle(GetAllCartProductsQuery query);
    CartProducts handle(GetCartProductbyCartIdAndProductIdQuery query);
}
