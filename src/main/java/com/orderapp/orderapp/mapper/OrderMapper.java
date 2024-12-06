package com.orderapp.orderapp.mapper;

import org.mapstruct.Mapper;

import com.orderapp.orderapp.entity.Order;
import com.orderapp.orderapp.response.OrderResponse;

@Mapper(componentModel = "spring", uses = { OrderProductMapper.class, CustomerMapper.class })
public interface OrderMapper {
    OrderResponse toOrderResponse(Order order);
}
