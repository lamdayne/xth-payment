package com.xth.xthpayment.dto.request;

import lombok.Data;

@Data
public class PaymentRequestDTO {
    private Long amount;
    private String description;
}
