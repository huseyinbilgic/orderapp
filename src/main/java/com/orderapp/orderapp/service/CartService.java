package com.orderapp.orderapp.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orderapp.orderapp.entity.Cart;
import com.orderapp.orderapp.entity.CartItem;
import com.orderapp.orderapp.entity.Customer;
import com.orderapp.orderapp.entity.Product;
import com.orderapp.orderapp.exceptionHandlers.errors.NotFoundException;
import com.orderapp.orderapp.mapper.CartMapper;
import com.orderapp.orderapp.repository.CartItemRepository;
import com.orderapp.orderapp.repository.CartRepository;
import com.orderapp.orderapp.repository.ProductRepository;
import com.orderapp.orderapp.request.AddProductToCart;
import com.orderapp.orderapp.response.CartResponse;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final CartMapper cartMapper;

    public void createCart(Customer customer){
        cartRepository.save(Cart.builder()
                .customer(customer)
                .totalPrice(BigDecimal.ZERO)
                .build());
    }

    public CartResponse fetchCartById(Long id) {
        Optional<Cart> cartById = cartRepository.findById(id);

        if (cartById.isPresent()) {
            return cartMapper.toCartResponse(cartById.get());
        }

        throw new NotFoundException("Invalid Cart ID");
    }

    public CartResponse addProductToCart(AddProductToCart addProductToCart) {
        Optional<Product> productById = productRepository.findById(addProductToCart.getProductId());
        if (productById.isPresent()) {
            Optional<Cart> cartByCustomerId = cartRepository.findById(addProductToCart.getCartId());

            if (cartByCustomerId.isPresent()) {

                CartItem cartItemOrNew = cartByCustomerId.get().getCartItems().stream()
                        .filter(cartItem -> cartItem.getProduct().getId() == addProductToCart.getProductId())
                        .filter(cartItem -> cartItem.getUnitPrice() == productById.get().getUnitPrice())
                        .findFirst()
                        .orElse(CartItem.builder().amount(addProductToCart.getAmount())
                                .unitPrice(productById.get().getUnitPrice())
                                .product(productById.get())
                                .build());

                if (cartItemOrNew.getId() == null) {
                    cartItemOrNew.setCart(cartByCustomerId.get());
                    cartByCustomerId.get().getCartItems().add(cartItemOrNew);
                } else {
                    cartItemOrNew.setAmount(cartItemOrNew.getAmount() + addProductToCart.getAmount());
                    cartItemRepository.save(cartItemOrNew);
                }

                cartByCustomerId.get().setTotalPrice(cartByCustomerId.get().getTotalPrice()
                        .add(cartItemOrNew.getUnitPrice().multiply(BigDecimal.valueOf(addProductToCart.getAmount()))));
                cartRepository.save(cartByCustomerId.get());
                
                return cartMapper.toCartResponse(cartByCustomerId.get());
            }
            throw new NotFoundException("Invalid Car Id");
        }
        throw new NotFoundException("Invalid Product Id");
    }

    public String emptyCart(Long id) {
        Optional<Cart> byId = cartRepository.findById(id);

        if (byId.isPresent()) {
            cartItemRepository.deleteAll(byId.get().getCartItems());
            byId.get().setCartItems(null);
            byId.get().setTotalPrice(BigDecimal.ZERO);
            cartRepository.save(byId.get());
            return "Empty Cart";
        }

        throw new NotFoundException("Invalid Cart Id");
    }
}
