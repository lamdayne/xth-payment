package com.xth.xthpayment.dto.response;

import lombok.Builder;
import lombok.Getter;

public class PaymentResponse {

    @Builder
    @Getter
    public static class VNPayResponse {
        private String code;
        private String message;
        private String paymentUrl;
    }
}
