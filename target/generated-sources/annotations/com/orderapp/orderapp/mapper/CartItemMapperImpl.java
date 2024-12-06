package com.orderapp.orderapp.mapper;

import com.orderapp.orderapp.entity.CartItem;
import com.orderapp.orderapp.entity.Product;
import com.orderapp.orderapp.response.CartItemResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-06T21:59:20+0300",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.40.0.z20241112-1021, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class CartItemMapperImpl implements CartItemMapper {

    @Override
    public CartItemResponse toCartItemResponse(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }

        CartItemResponse.CartItemResponseBuilder cartItemResponse = CartItemResponse.builder();

        cartItemResponse.productName( cartItemProductName( cartItem ) );
        cartItemResponse.amount( cartItem.getAmount() );
        cartItemResponse.id( cartItem.getId() );
        cartItemResponse.unitPrice( cartItem.getUnitPrice() );

        return cartItemResponse.build();
    }

    private String cartItemProductName(CartItem cartItem) {
        Product product = cartItem.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getName();
    }
}
