package com.orderapp.orderapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderapp.orderapp.request.AddCustomerRequest;
import com.orderapp.orderapp.response.CustomerResponse;
import com.orderapp.orderapp.service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> addCustomer(@Valid @RequestBody AddCustomerRequest addCustomerRequest) {
        return ResponseEntity.ok(customerService.addCustomer(addCustomerRequest));
    }
}
