package com.orderapp.orderapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.orderapp.orderapp.entity.CartItem;
import com.orderapp.orderapp.response.CartItemResponse;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    @Mapping(source = "product.name", target = "productName")
    CartItemResponse toCartItemResponse(CartItem cartItem);
}
