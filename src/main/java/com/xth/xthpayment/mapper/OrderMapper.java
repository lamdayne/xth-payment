package com.xth.xthpayment.mapper;

import com.xth.xthpayment.dto.request.CreateOrderRequest;
import com.xth.xthpayment.dto.request.UpdateOrderRequest;
import com.xth.xthpayment.dto.response.OrderResponse;
import com.xth.xthpayment.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toOrder(CreateOrderRequest request);
    OrderResponse toOrderResponse(Order order);
    void updateOrder(@MappingTarget Order order, UpdateOrderRequest request);
    List<OrderResponse> toOrderResponseList(List<Order> orders);
}
