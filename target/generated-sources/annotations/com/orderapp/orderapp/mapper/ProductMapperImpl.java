package com.orderapp.orderapp.mapper;

import com.orderapp.orderapp.entity.Product;
import com.orderapp.orderapp.request.ProductRequest;
import com.orderapp.orderapp.response.ProductResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-06T21:59:20+0300",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.40.0.z20241112-1021, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductResponse toProductResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponse.ProductResponseBuilder productResponse = ProductResponse.builder();

        productResponse.id( product.getId() );
        productResponse.name( product.getName() );
        productResponse.stock( product.getStock() );
        productResponse.unitPrice( product.getUnitPrice() );

        return productResponse.build();
    }

    @Override
    public Product toProduct(ProductRequest productRequest) {
        if ( productRequest == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.name( productRequest.getName() );
        product.stock( productRequest.getStock() );
        product.unitPrice( productRequest.getUnitPrice() );

        return product.build();
    }
}
