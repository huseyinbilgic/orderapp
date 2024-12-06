package com.orderapp.orderapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.orderapp.orderapp.entity.CartItem;
import com.orderapp.orderapp.entity.OrderProduct;
import com.orderapp.orderapp.response.OrderProductResponse;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {

    @Mapping(target = "order", ignore = true)
    OrderProduct toOrderProduct(CartItem cartItem);

    @Mapping(source = "product.name", target = "productName")
    OrderProductResponse toOrderProductResponse(OrderProduct orderProduct);
}
