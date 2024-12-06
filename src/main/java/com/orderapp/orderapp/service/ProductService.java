package com.orderapp.orderapp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.orderapp.orderapp.entity.Product;
import com.orderapp.orderapp.exceptionHandlers.errors.NotFoundException;
import com.orderapp.orderapp.mapper.ProductMapper;
import com.orderapp.orderapp.repository.CartItemRepository;
import com.orderapp.orderapp.repository.OrderProductRepository;
import com.orderapp.orderapp.repository.ProductRepository;
import com.orderapp.orderapp.request.ProductRequest;
import com.orderapp.orderapp.response.ProductResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CartItemRepository cartItemRepository;
    private final OrderProductRepository orderProductRepository;

    public List<ProductResponse> fetchAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> productMapper.toProductResponse(product))
                .collect(Collectors.toList());
    }

    public ProductResponse fetchProductById(Long id) throws Exception {
        Optional<Product> productById = productRepository.findById(id);

        if (productById.isPresent()) {
            return productMapper.toProductResponse(productById.get());
        }

        throw new NotFoundException("Invalid product ID");
    }

    public ProductResponse saveProduct(Long id, ProductRequest productRequest) {
        Product product = productMapper.toProduct(productRequest);
        if (id != null) {
            product.setId(id);
        }

        productRepository.save(product);

        return productMapper.toProductResponse(product);
    }

    public String deleteProduct(Long id) {
        Optional<Product> productById = productRepository.findById(id);
        if (productById.isPresent()) {
            orderProductRepository.dissociateProductFromOrderProducts(id);
            cartItemRepository.dissociateProductFromCartItems(id);
            productRepository.delete(productById.get());
            return "Deleted with ID " + id;
        }
        throw new NotFoundException("Invalid product ID");

    }

    public void reduceStock(Long id, Integer amount) {
        Optional<Product> byId = productRepository.findById(id);

        if (byId.isPresent()) {
            byId.get().setStock(byId.get().getStock() - amount);
            productRepository.save(byId.get());
        }
        else{
            throw new NotFoundException("Invalid product ID");
        }
    }
}
