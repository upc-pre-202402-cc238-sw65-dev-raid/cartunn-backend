package com.thecoders.cartunnbackend.payment.application.internal.commandservices;

import com.thecoders.cartunnbackend.payment.domain.model.aggregates.Cart;
import com.thecoders.cartunnbackend.payment.domain.model.aggregates.CartProducts;
import com.thecoders.cartunnbackend.payment.domain.model.commands.*;
import com.thecoders.cartunnbackend.payment.domain.model.queries.GetCartProductsByCartIdQuery;
import com.thecoders.cartunnbackend.payment.domain.services.CartCommandService;
import com.thecoders.cartunnbackend.payment.domain.services.CartProductCommandService;
import com.thecoders.cartunnbackend.payment.infrastructure.persistence.jpa.repositories.CartProductRepository;
import com.thecoders.cartunnbackend.payment.infrastructure.persistence.jpa.repositories.CartRepository;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartProductsCommandServiceImpl  implements CartProductCommandService {
    private final CartProductRepository cartProductRepository;

    public CartProductsCommandServiceImpl(CartProductRepository cartProductRepository) {this.cartProductRepository = cartProductRepository;}

    @Override
    public CartProducts handle(CreateCartProductsCommand command) {
        var cartProduct = new CartProducts(command);
        try {
            cartProductRepository.save(cartProduct);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving cart: " + e.getMessage());
        }
        return cartProduct;
    }
    @Override
    public Optional<CartProducts> handle(UpdateCartProductsCommand command) {

        var result = cartProductRepository.findByCartIdProductId(command.cartId(), command.productId());
        if (result == null) throw new IllegalArgumentException("Cart product does not exist");
        try {
            var updatedCartProduct = cartProductRepository.save(result.updateInformation(command.cartId(), command.productId()));
            return Optional.of(updatedCartProduct);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating cart product: " + e.getMessage());
        }
    }
    @Override
    public void handle(DeleteCartProductsCommand command) {
        if (cartProductRepository.findByCartIdProductId(command.CartId(), command.ProductId())!=null) {
            throw new IllegalArgumentException("Cart product does not exist");
        }
        try {
            CartProducts cartProducts = new CartProducts(command.CartId(),command.ProductId());
            cartProductRepository.delete(cartProducts);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting cart product: " + e.getMessage());
        }
    }
    @Override
    public CartProducts handle(GetCartProductCommand command) {
        if (cartProductRepository.findByCartIdProductId(command.CartId(), command.ProductId())!=null) {
            throw new IllegalArgumentException("Cart product does not exist");
        }
        try {
            CartProducts cartProducts = new CartProducts(command.CartId(),command.ProductId());
            return cartProducts;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting cart product: " + e.getMessage());
        }
    }
}
