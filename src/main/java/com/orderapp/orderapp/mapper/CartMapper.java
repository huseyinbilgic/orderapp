package com.orderapp.orderapp.mapper;

import org.mapstruct.Mapper;

import com.orderapp.orderapp.entity.Cart;
import com.orderapp.orderapp.response.CartResponse;

@Mapper(componentModel = "spring", uses = { CartItemMapper.class, CustomerMapper.class })
public interface CartMapper {
    CartResponse toCartResponse(Cart cart);
}
