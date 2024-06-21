package com.thecoders.cartunnbackend.payment.application.internal.queryservices;

import com.thecoders.cartunnbackend.payment.domain.model.aggregates.Cart;
import com.thecoders.cartunnbackend.payment.domain.model.aggregates.CartProducts;
import com.thecoders.cartunnbackend.payment.domain.model.queries.*;
import com.thecoders.cartunnbackend.payment.domain.services.CartProductQueryService;
import com.thecoders.cartunnbackend.payment.domain.services.CartQueryService;
import com.thecoders.cartunnbackend.payment.infrastructure.persistence.jpa.repositories.CartProductRepository;
import com.thecoders.cartunnbackend.payment.infrastructure.persistence.jpa.repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CartProductsQueryServiceImpl implements CartProductQueryService {
    private final CartProductRepository cartProductRepository;

    public CartProductsQueryServiceImpl(CartProductRepository cartProductRepository) {this.cartProductRepository = cartProductRepository;}

    @Override
    public List<CartProducts> handle(GetCartProductsByCartIdQuery query) {return cartProductRepository.findByCartId(query.CartId());}
    @Override
    public List<CartProducts> handle(GetAllCartProductsQuery query) {return cartProductRepository.findAll();}
    @Override
    public CartProducts handle(GetCartProductbyCartIdAndProductIdQuery query) {return cartProductRepository.findByCartIdProductId(query.CartId(),query.ProductId());}
}
