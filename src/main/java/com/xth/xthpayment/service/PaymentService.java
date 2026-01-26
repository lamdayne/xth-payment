package com.xth.xthpayment.service;

import com.xth.xthpayment.dto.response.PaymentResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface PaymentService {

    PaymentResponse.VNPayResponse createVNPayPayment(HttpServletRequest request);
}
