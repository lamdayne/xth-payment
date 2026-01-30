package com.xth.xthpayment.service.impl;

import com.xth.xthpayment.config.payment.PayOSConfig;
import com.xth.xthpayment.config.payment.VNPayConfig;
import com.xth.xthpayment.dto.request.PaymentRequestDTO;
import com.xth.xthpayment.dto.response.PaymentResponse;
import com.xth.xthpayment.service.PaymentService;
import com.xth.xthpayment.util.VNPayUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.payos.PayOS;
import vn.payos.model.v2.paymentRequests.CreatePaymentLinkRequest;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final VNPayConfig vnPayConfig;
    private final PayOSConfig payOSConfig;

    @Override
    public PaymentResponse.VNPayResponse createVNPayPayment(HttpServletRequest request) {
        long amount = Long.parseLong(request.getParameter("amount")) * 100L;
        String bankCode = request.getParameter("bankCode");

        String orderId = VNPayUtil.getRandomNumber(5);

        Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig(orderId);
        vnpParamsMap.put("vnp_Amount", String.valueOf(amount));
        if (bankCode != null && !bankCode.isEmpty()) {
            vnpParamsMap.put("vnp_BankCode", bankCode);
        }

        vnpParamsMap.put("vnp_IpAddr", VNPayUtil.getIpAddress(request));
        String queryUrl = VNPayUtil.getPaymentURL(vnpParamsMap, true);
        String hashData = VNPayUtil.getPaymentURL(vnpParamsMap, false);
        String vnpSecureHash = VNPayUtil.hmacSHA512(vnPayConfig.getVnp_SecretKey(), hashData);
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
        String paymentUrl = vnPayConfig.getVnp_PayUrl() + "?" + queryUrl;


        return PaymentResponse.VNPayResponse.builder()
                .code("ok")
                .message("success")
                .paymentUrl(paymentUrl).build();
    }

    @Override
    public PaymentResponse.PayOSResponse createPayOSPayment(PaymentRequestDTO request) {
        PayOS payOS = payOSConfig.payOS();
        CreatePaymentLinkRequest paymentRequest = CreatePaymentLinkRequest.builder()
                .orderCode(System.currentTimeMillis() / 1000)
                .amount(request.getAmount())
                .description(request.getDescription())
                .cancelUrl(payOSConfig.getCancelUrl())
                .returnUrl(payOSConfig.getReturnUrl())
                .build();

        var paymentLink = payOS.paymentRequests().create(paymentRequest);
        System.out.println("paymentLink: " + paymentLink.getCheckoutUrl());
        // Format: https://img.vietqr.io/image/<BANK_ID>-<ACCOUNT_NO>-vietqr_pro.jpg?addInfo=<DESCRIPTION>&amount=<AMOUNT>
        String qrImageUrl = String.format(
                "https://img.vietqr.io/image/%s-%s-vietqr_pro.jpg?addInfo=%s&amount=%d",
                payOSConfig.getBankId(),
                paymentLink.getAccountNumber(),
                paymentLink.getDescription(),
                paymentLink.getAmount()
        );

        System.out.println(qrImageUrl);

        return PaymentResponse.PayOSResponse.builder()
                .paymentUrl(paymentLink.getCheckoutUrl())
                .qrImageUrl(qrImageUrl)
                .build();
    }
}
