package com.xth.xthpayment.service;

import com.xth.xthpayment.constant.OrderStatus;
import com.xth.xthpayment.dto.request.CreateOrderRequest;
import com.xth.xthpayment.dto.request.UpdateOrderRequest;
import com.xth.xthpayment.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(CreateOrderRequest request);
    OrderResponse updateOrder(String orderId, UpdateOrderRequest request);
    void deleteOrder(String orderId);
    List<OrderResponse> getAllOrders();
    OrderResponse getOrderById(String orderId);
    void updateStatusByOrderCode(String orderCode, String status);
    String getStatusByOrderCode(String orderCode);
    OrderResponse getOrderByOrderCodeAndPhone(String orderCode, String phone);
}
