package com.xth.xthpayment.controller;

import com.xth.xthpayment.dto.request.CreateOrderRequest;
import com.xth.xthpayment.dto.request.UpdateOrderRequest;
import com.xth.xthpayment.dto.response.ApiResponse;
import com.xth.xthpayment.dto.response.OrderResponse;
import com.xth.xthpayment.service.OrderService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ApiResponse<OrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        return ApiResponse.<OrderResponse>builder()
                .data(orderService.createOrder(request))
                .build();
    }

    @PutMapping("/{orderId}")
    public ApiResponse<OrderResponse> updateOrder(@PathVariable String orderId, @RequestBody UpdateOrderRequest request) {
        return ApiResponse.<OrderResponse>builder()
                .data(orderService.updateOrder(orderId, request))
                .build();
    }

    @DeleteMapping("/{orderId}")
    public ApiResponse<OrderResponse> deleteOrder(@PathVariable String orderId) {
        return ApiResponse.<OrderResponse>builder()
                .message("Order has been deleted")
                .build();
    }

    @GetMapping
    public ApiResponse<List<OrderResponse>> getAllOrders() {
        return ApiResponse.<List<OrderResponse>>builder()
                .data(orderService.getAllOrders())
                .build();
    }

    @GetMapping("/{orderId}")
    public ApiResponse<OrderResponse> getOrder(@PathVariable String orderId) {
        return ApiResponse.<OrderResponse>builder()
                .data(orderService.getOrderById(orderId))
                .build();
    }

    @GetMapping("/{orderCode}/status")
    public ApiResponse<String> checkOrderStatus(@PathVariable String orderCode) {
        return ApiResponse.<String>builder()
                .data(orderService.getStatusByOrderCode(orderCode))
                .build();
    }

    @GetMapping("/track")
    public ApiResponse<OrderResponse> getTrackOrder(@RequestParam String orderCode,
                                                    @RequestParam String phone
    ) {
        return ApiResponse.<OrderResponse>builder()
                .data(orderService.getOrderByOrderCodeAndPhone(orderCode, phone))
                .build();
    }

}
