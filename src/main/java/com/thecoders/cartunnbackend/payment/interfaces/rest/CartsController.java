package com.thecoders.cartunnbackend.payment.interfaces.rest;

import com.thecoders.cartunnbackend.payment.domain.model.commands.DeleteCartCommand;
import com.thecoders.cartunnbackend.payment.domain.model.queries.GetAllCartsQuery;
import com.thecoders.cartunnbackend.payment.domain.model.queries.GetCartByIdQuery;
import com.thecoders.cartunnbackend.payment.domain.services.CartCommandService;
import com.thecoders.cartunnbackend.payment.domain.services.CartQueryService;
import com.thecoders.cartunnbackend.payment.interfaces.rest.resources.CartResource;
import com.thecoders.cartunnbackend.payment.interfaces.rest.resources.CreateCartResource;
import com.thecoders.cartunnbackend.payment.interfaces.rest.resources.UpdateCartResource;
import com.thecoders.cartunnbackend.payment.interfaces.rest.transform.CartResourceFromEntityAssembler;
import com.thecoders.cartunnbackend.payment.interfaces.rest.transform.CreateCartCommandFromResourceAssembler;
import com.thecoders.cartunnbackend.payment.interfaces.rest.transform.UpdateCartCommandFromResourceAssembler;
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

    public CartsController(CartCommandService cartCommandService, CartQueryService cartQueryService) {
        this.cartCommandService = cartCommandService;
        this.cartQueryService = cartQueryService;
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
}
