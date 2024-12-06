package com.orderapp.orderapp.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orderapp.orderapp.entity.Cart;
import com.orderapp.orderapp.entity.CartItem;
import com.orderapp.orderapp.exceptionHandlers.errors.NotFoundException;
import com.orderapp.orderapp.repository.CartItemRepository;
import com.orderapp.orderapp.repository.CartRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    public String deleteItem(Long id) {
        Optional<CartItem> byId = cartItemRepository.findById(id);

        if (byId.isPresent()) {
            BigDecimal multiply = byId.get().getUnitPrice().multiply(BigDecimal.valueOf(byId.get().getAmount()));
            Long cartId = byId.get().getCart().getId();
            cartItemRepository.delete(byId.get());

            Optional<Cart> cart = cartRepository.findById(cartId);
            if (cart.isPresent()) {
                cart.get().setTotalPrice(cart.get().getTotalPrice().subtract(multiply));
                cartRepository.save(cart.get());
                return "Deleted with ID " + id;
            }
        }

        throw new NotFoundException("invalid Cart item id");
    }
}
