package com.orderapp.orderapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderapp.orderapp.response.OrderResponse;
import com.orderapp.orderapp.service.OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> fetchOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.fetchOrder(id));
    }

    @GetMapping("/getOrderForCode/{code}")
    public ResponseEntity<OrderResponse> fetchOrderForCode(@PathVariable String code) {
        return ResponseEntity.ok(orderService.fetchOrderByCode(code));
    }

    @GetMapping("/getAllOrdersForCustomer/{customerId}")
    public ResponseEntity<List<OrderResponse>> fetchAllOrdersForCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(orderService.fetchAllOrdersForCustomer(customerId));
    }

    @PostMapping("/placeOrder/{cartId}")
    private ResponseEntity<OrderResponse> placeOrder(@PathVariable Long cartId) {
        return ResponseEntity.ok(orderService.placeOrder(cartId));
    }
}
