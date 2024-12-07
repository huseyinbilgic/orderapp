package com.orderapp.orderapp.mapper;

import com.orderapp.orderapp.entity.CartItem;
import com.orderapp.orderapp.entity.OrderProduct;
import com.orderapp.orderapp.entity.Product;
import com.orderapp.orderapp.response.OrderProductResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-07T11:39:27+0300",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.40.0.z20241112-1021, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class OrderProductMapperImpl implements OrderProductMapper {

    @Override
    public OrderProduct toOrderProduct(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }

        OrderProduct.OrderProductBuilder orderProduct = OrderProduct.builder();

        orderProduct.amount( cartItem.getAmount() );
        orderProduct.product( cartItem.getProduct() );
        orderProduct.unitPrice( cartItem.getUnitPrice() );

        return orderProduct.build();
    }

    @Override
    public OrderProductResponse toOrderProductResponse(OrderProduct orderProduct) {
        if ( orderProduct == null ) {
            return null;
        }

        OrderProductResponse.OrderProductResponseBuilder orderProductResponse = OrderProductResponse.builder();

        orderProductResponse.productName( orderProductProductName( orderProduct ) );
        orderProductResponse.amount( orderProduct.getAmount() );
        orderProductResponse.id( orderProduct.getId() );
        orderProductResponse.unitPrice( orderProduct.getUnitPrice() );

        return orderProductResponse.build();
    }

    private String orderProductProductName(OrderProduct orderProduct) {
        Product product = orderProduct.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getName();
    }
}
