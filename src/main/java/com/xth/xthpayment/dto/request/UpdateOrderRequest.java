package com.xth.xthpayment.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderRequest {
    private String phone;
    private String email;
    private String fullName;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String status;
    private String productId;
    private Long totalPrice;
}
