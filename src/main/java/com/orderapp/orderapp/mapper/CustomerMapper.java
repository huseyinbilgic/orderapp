package com.orderapp.orderapp.mapper;

import org.mapstruct.Mapper;

import com.orderapp.orderapp.entity.Customer;
import com.orderapp.orderapp.request.AddCustomerRequest;
import com.orderapp.orderapp.response.CustomerResponse;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerResponse toCustomerResponse(Customer customer);

    Customer toCustomer(AddCustomerRequest addCustomerRequest);
}
