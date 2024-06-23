package com.thecoders.cartunnbackend.payment.interfaces.rest;

import com.thecoders.cartunnbackend.payment.domain.model.commands.DeleteCartCommand;
import com.thecoders.cartunnbackend.payment.domain.model.queries.*;
import com.thecoders.cartunnbackend.payment.domain.services.CartCommandService;
import com.thecoders.cartunnbackend.payment.domain.services.CartQueryService;
import com.thecoders.cartunnbackend.payment.interfaces.rest.resources.*;
import com.thecoders.cartunnbackend.payment.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/carts", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Carts", description = "Cart Management Endpoints")
public class CartsController {
    private final CartCommandService cartCommandService;
    private final CartQueryService cartQueryService;
    //private final CartProductCommandService cartProductCommandService;
    //private final CartProductQueryService cartProductQueryService;

    public CartsController(CartCommandService cartCommandService, CartQueryService cartQueryService) {
        this.cartCommandService = cartCommandService;
        this.cartQueryService = cartQueryService;
        //this.cartProductCommandService = cartProductCommandService;
        //this.cartProductQueryService = cartProductQueryService;
    }
    @PostMapping
    public ResponseEntity<CartResource> createCart(@RequestBody CreateCartResource createCartResource) {
        var createCartCommand = CreateCartCommandFromResourceAssembler.toCommandFromResource(createCartResource);
        var cartId = cartCommandService.handle(createCartCommand);
        if (cartId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getCartByIdQuery = new GetCartByIdQuery(cartId);
        var cart = cartQueryService.handle(getCartByIdQuery);
        if (cart.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var cartResource = CartResourceFromEntityAssembler.toResourceFromEntity(cart.get());
        return new ResponseEntity<>(cartResource, HttpStatus.CREATED);
    }
    @GetMapping("/{cartId}")
    public ResponseEntity<CartResource> getCart(@PathVariable Long cartId) {
        var getCartByIdQuery = new GetCartByIdQuery(cartId);
        var cart = cartQueryService.handle(getCartByIdQuery);
        if (cart.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var cartResource = CartResourceFromEntityAssembler.toResourceFromEntity(cart.get());
        return ResponseEntity.ok(cartResource);
    }
    @GetMapping
    public ResponseEntity<List<CartResource>> getAllCarts() {
        var getAllCartsQuery = new GetAllCartsQuery();
        var carts = cartQueryService.handle(getAllCartsQuery);
        var cartResources = carts.stream().map(CartResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(cartResources);
    }
    @PutMapping("/{cartId}")
    public ResponseEntity<CartResource> updateCart(@PathVariable Long cartId, @RequestBody UpdateCartResource updateCartResource) {
        var updateCartCommand = UpdateCartCommandFromResourceAssembler.toCommandFromResource(cartId, updateCartResource);
        var updatedCart = cartCommandService.handle(updateCartCommand);
        if (updatedCart.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var cartResource = CartResourceFromEntityAssembler.toResourceFromEntity(updatedCart.get());
        return ResponseEntity.ok(cartResource);
    }
    @DeleteMapping("/{cartId}")
    public ResponseEntity<?> deleteCart(@PathVariable Long cartId) {
        var deleteCartCommand = new DeleteCartCommand(cartId);
        cartCommandService.handle(deleteCartCommand);
        return ResponseEntity.ok("Cart deleted successfully");
    }
    /*@GetMapping
    public ResponseEntity<List<CartProductResource>> getAllCartProducts() {
        var getAllCartProductsQuery = new GetAllCartProductsQuery();
        var carts = cartProductQueryService.handle(getAllCartProductsQuery);
        var cartProdutsResources = carts.stream().map(CartProductResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(cartProdutsResources);
    }
    @DeleteMapping("/{cartId}/{productId}")
    public ResponseEntity<?> deleteCartProduct(@PathVariable Long cartId, Long productId) {
        var deleteCartProductsCommand = new DeleteCartProductsCommand(cartId,productId);
        cartProductCommandService.handle(deleteCartProductsCommand);
        return ResponseEntity.ok("CartProduct deleted successfully");
    }
    @PostMapping
    public ResponseEntity<CartProductResource> createCartProduct(@RequestBody CreateCartProductResource createCartProductResource) {
        var createCartProductCommand = CreateCartProductCommandFromResourceAssembler.toCommandFromResource(createCartProductResource);
        CartProducts cartProducts = cartProductCommandService.handle(createCartProductCommand);
        if (cartProducts.getCartId() == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getCartProductByCartIdAndProductIdQuery = new GetCartProductbyCartIdAndProductIdQuery(cartProducts.getCartId(),cartProducts.getProductId());
        var cartProduct = cartProductQueryService.handle(getCartProductByCartIdAndProductIdQuery);
        if (cartProduct!=null) {
            return ResponseEntity.badRequest().build();
        }
        var cartProductResource = CartProductResourceFromEntityAssembler.toResourceFromEntity(cartProduct);
        return new ResponseEntity<>(cartProductResource, HttpStatus.CREATED);

    }*/
}
