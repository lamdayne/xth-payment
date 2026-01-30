package com.xth.xthpayment.service.impl;

import com.xth.xthpayment.constant.OrderStatus;
import com.xth.xthpayment.dto.request.CreateOrderRequest;
import com.xth.xthpayment.dto.request.UpdateOrderRequest;
import com.xth.xthpayment.dto.response.OrderResponse;
import com.xth.xthpayment.entity.Order;
import com.xth.xthpayment.exception.AppException;
import com.xth.xthpayment.exception.ErrorCode;
import com.xth.xthpayment.mapper.OrderMapper;
import com.xth.xthpayment.repository.OrderRepository;
import com.xth.xthpayment.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponse createOrder(CreateOrderRequest request) {
        Order order = orderMapper.toOrder(request);
        order.setOrderCode("OD" + System.currentTimeMillis());
        order.setOrderDate(new Date());
        return orderMapper.toOrderResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponse updateOrder(String orderId, UpdateOrderRequest request) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        orderMapper.updateOrder(order, request);
        return orderMapper.toOrderResponse(orderRepository.save(order));
    }

    @Override
    public void deleteOrder(String orderId) {
        orderRepository.findById(orderId).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderMapper.toOrderResponseList(orderRepository.findAll());
    }

    @Override
    public OrderResponse getOrderById(String orderId) {
        return orderMapper.toOrderResponse(orderRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND))
        );
    }

    @Override
    public void updateStatusByOrderCode(String orderCode, String status) {
        Order order = orderRepository.findByOrderCode(orderCode).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        order.setStatus(status);
        orderRepository.save(order);
    }

    @Override
    public String getStatusByOrderCode(String orderCode) {
        return orderRepository.findByOrderCode(orderCode)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND))
                .getStatus();
    }
}
