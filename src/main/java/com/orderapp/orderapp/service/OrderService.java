package com.orderapp.orderapp.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orderapp.orderapp.entity.Cart;
import com.orderapp.orderapp.entity.CartItem;
import com.orderapp.orderapp.entity.Order;
import com.orderapp.orderapp.entity.OrderProduct;
import com.orderapp.orderapp.exceptionHandlers.errors.NotFoundException;
import com.orderapp.orderapp.mapper.OrderMapper;
import com.orderapp.orderapp.mapper.OrderProductMapper;
import com.orderapp.orderapp.repository.CartRepository;
import com.orderapp.orderapp.repository.CustomerRepository;
import com.orderapp.orderapp.repository.OrderProductRepository;
import com.orderapp.orderapp.repository.OrderRepository;
import com.orderapp.orderapp.response.OrderResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderMapper orderMapper;
    private final OrderProductMapper orderProductMapper;
    private final OrderProductRepository orderProductRepository;
    private final CustomerRepository customerRepository;
    private final ProductService productService;
    private final CartService cartService;

    public OrderResponse fetchOrder(Long id) {
        Optional<Order> byId = orderRepository.findById(id);

        if (byId.isPresent()) {
            return orderMapper.toOrderResponse(byId.get());
        }

        throw new NotFoundException("Invalid order id");
    }

    public OrderResponse fetchOrderByCode(String code) {
        Optional<Order> byId = orderRepository.findByCode(code);

        if (byId.isPresent()) {
            return orderMapper.toOrderResponse(byId.get());
        }

        throw new NotFoundException("Invalid order code");
    }

    public List<OrderResponse> fetchAllOrdersForCustomer(Long customerId) {
        if (customerRepository.existsById(customerId)) {
            List<Order> orders = orderRepository.findByCustomerId(customerId);

            return orders.stream().map(order -> orderMapper.toOrderResponse(order)).toList();
        }
        throw new NotFoundException("Invalid customer id");
    }

    public OrderResponse placeOrder(Long cartId) {
        Optional<Cart> cartById = cartRepository.findById(cartId);

        if (cartById.isPresent()) {
            if (!cartById.get().getCartItems().isEmpty()) {
                if (checkStocks(cartById.get().getCartItems())) {
                    Order order = Order.builder()
                            .code(UUID.randomUUID().toString())
                            .customer(cartById.get().getCustomer())
                            .totalPrice(cartById.get().getTotalPrice())
                            .build();
                    orderRepository.save(order);

                    List<OrderProduct> orderProducts = cartById.get().getCartItems().stream()
                            .map(item -> orderProductMapper.toOrderProduct(item))
                            .peek(t -> {
                                t.setId(null);
                                t.setOrder(order);
                                productService.reduceStock(t.getProduct().getId(), t.getAmount());
                            })
                            .toList();
                    orderProductRepository.saveAll(orderProducts);

                    order.setOrderProducts(orderProducts);

                    cartService.emptyCart(cartId);

                    return orderMapper.toOrderResponse(order);
                }
                throw new NotFoundException("Some products do not have enough stock.");
            }

            throw new NotFoundException("There are no products in your cart.");
        }
        throw new NotFoundException("Invalid cart id");
    }

    private boolean checkStocks(List<CartItem> cartItems){
        return cartItems.stream().filter(t -> t.getAmount() > t.getProduct().getStock()).toList().isEmpty();
    }
}
