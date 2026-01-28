package com.xth.xthpayment.service;

import com.xth.xthpayment.dto.response.PaymentResponse;
import jakarta.servlet.http.HttpServletRequest;
import vn.payos.model.v2.paymentRequests.CreatePaymentLinkRequest;

public interface PaymentService {

    PaymentResponse.VNPayResponse createVNPayPayment(HttpServletRequest request);

    String createPaymentLink(HttpServletRequest request);
}
