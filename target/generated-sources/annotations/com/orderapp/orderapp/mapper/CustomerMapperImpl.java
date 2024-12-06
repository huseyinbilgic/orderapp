package com.orderapp.orderapp.mapper;

import com.orderapp.orderapp.entity.Customer;
import com.orderapp.orderapp.request.AddCustomerRequest;
import com.orderapp.orderapp.response.CustomerResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-06T21:59:20+0300",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.40.0.z20241112-1021, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerResponse toCustomerResponse(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerResponse.CustomerResponseBuilder customerResponse = CustomerResponse.builder();

        customerResponse.id( customer.getId() );
        customerResponse.name( customer.getName() );

        return customerResponse.build();
    }

    @Override
    public Customer toCustomer(AddCustomerRequest addCustomerRequest) {
        if ( addCustomerRequest == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.name( addCustomerRequest.getName() );

        return customer.build();
    }
}
