package com.orderapp.orderapp.mapper;

import org.mapstruct.Mapper;

import com.orderapp.orderapp.entity.Product;
import com.orderapp.orderapp.request.ProductRequest;
import com.orderapp.orderapp.response.ProductResponse;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse toProductResponse(Product product);

    Product toProduct(ProductRequest productRequest);
}
