package com.orderapp.orderapp.mapper;

import com.orderapp.orderapp.entity.Order;
import com.orderapp.orderapp.entity.OrderProduct;
import com.orderapp.orderapp.response.OrderProductResponse;
import com.orderapp.orderapp.response.OrderResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-07T11:39:27+0300",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.40.0.z20241112-1021, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private OrderProductMapper orderProductMapper;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public OrderResponse toOrderResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderResponse.OrderResponseBuilder orderResponse = OrderResponse.builder();

        orderResponse.code( order.getCode() );
        orderResponse.customer( customerMapper.toCustomerResponse( order.getCustomer() ) );
        orderResponse.id( order.getId() );
        orderResponse.orderProducts( orderProductListToOrderProductResponseList( order.getOrderProducts() ) );
        orderResponse.totalPrice( order.getTotalPrice() );

        return orderResponse.build();
    }

    protected List<OrderProductResponse> orderProductListToOrderProductResponseList(List<OrderProduct> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderProductResponse> list1 = new ArrayList<OrderProductResponse>( list.size() );
        for ( OrderProduct orderProduct : list ) {
            list1.add( orderProductMapper.toOrderProductResponse( orderProduct ) );
        }

        return list1;
    }
}
