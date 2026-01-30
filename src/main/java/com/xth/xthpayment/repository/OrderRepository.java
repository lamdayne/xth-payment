package com.xth.xthpayment.repository;

import com.xth.xthpayment.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, String> {

    Optional<Order> findByOrderCode(String orderCode);
}
