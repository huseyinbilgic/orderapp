package com.orderapp.orderapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderapp.orderapp.request.AddProductToCart;
import com.orderapp.orderapp.response.CartResponse;
import com.orderapp.orderapp.service.CartService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> fetchCartById(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.fetchCartById(id));
    }

    @PostMapping("/addProductToCart")
    public ResponseEntity<CartResponse> postMethodName(@Valid @RequestBody AddProductToCart addProductToCart) {
        return ResponseEntity.ok(cartService.addProductToCart(addProductToCart));
    }

    @PutMapping("/emptyCart/{id}")
    public ResponseEntity<String> postMethodName(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.emptyCart(id));
    }
}
