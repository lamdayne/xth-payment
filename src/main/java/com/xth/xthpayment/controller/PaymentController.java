package com.xth.xthpayment.controller;

import com.xth.xthpayment.dto.response.ApiResponse;
import com.xth.xthpayment.dto.response.PaymentResponse;
import com.xth.xthpayment.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/vn-pay")
    public ApiResponse<PaymentResponse.VNPayResponse> pay(HttpServletRequest request) {
        return ApiResponse.<PaymentResponse.VNPayResponse>builder()
                .message("success")
                .data(paymentService.createVNPayPayment(request))
                .build();
    }
}
