package com.xth.xthpayment.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private String id;
    private String orderCode;
    private String phone;
    private String email;
    private String fullName;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String status;
}
