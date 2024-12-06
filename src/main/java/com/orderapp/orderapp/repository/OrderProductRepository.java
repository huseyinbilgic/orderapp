package com.orderapp.orderapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.orderapp.orderapp.entity.OrderProduct;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE OrderProduct c SET c.product = null WHERE c.product.id = :productId")
    void dissociateProductFromOrderProducts(Long productId);
}
