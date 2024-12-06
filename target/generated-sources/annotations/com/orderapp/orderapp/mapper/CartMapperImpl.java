package com.orderapp.orderapp.mapper;

import com.orderapp.orderapp.entity.Cart;
import com.orderapp.orderapp.entity.CartItem;
import com.orderapp.orderapp.response.CartItemResponse;
import com.orderapp.orderapp.response.CartResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-06T21:59:20+0300",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.40.0.z20241112-1021, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class CartMapperImpl implements CartMapper {

    @Autowired
    private CartItemMapper cartItemMapper;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public CartResponse toCartResponse(Cart cart) {
        if ( cart == null ) {
            return null;
        }

        CartResponse.CartResponseBuilder cartResponse = CartResponse.builder();

        cartResponse.cartItems( cartItemListToCartItemResponseList( cart.getCartItems() ) );
        cartResponse.customer( customerMapper.toCustomerResponse( cart.getCustomer() ) );
        cartResponse.id( cart.getId() );
        cartResponse.totalPrice( cart.getTotalPrice() );

        return cartResponse.build();
    }

    protected List<CartItemResponse> cartItemListToCartItemResponseList(List<CartItem> list) {
        if ( list == null ) {
            return null;
        }

        List<CartItemResponse> list1 = new ArrayList<CartItemResponse>( list.size() );
        for ( CartItem cartItem : list ) {
            list1.add( cartItemMapper.toCartItemResponse( cartItem ) );
        }

        return list1;
    }
}
