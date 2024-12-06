package com.orderapp.orderapp.service;

import org.springframework.stereotype.Service;

import com.orderapp.orderapp.entity.Customer;
import com.orderapp.orderapp.mapper.CustomerMapper;
import com.orderapp.orderapp.repository.CustomerRepository;
import com.orderapp.orderapp.request.AddCustomerRequest;
import com.orderapp.orderapp.response.CustomerResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CartService cartService;

    public CustomerResponse addCustomer(AddCustomerRequest addCustomerRequest) {
        Customer customer = customerMapper.toCustomer(addCustomerRequest);

        customerRepository.save(customer);
        cartService.createCart(customer);
        return customerMapper.toCustomerResponse(customer);
    }
}
