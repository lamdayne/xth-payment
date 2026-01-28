package com.xth.xthpayment.controller;

import com.xth.xthpayment.config.payment.PayOSConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.payos.PayOS;
import vn.payos.model.webhooks.Webhook;

@RestController
@RequiredArgsConstructor
public class PayOSWebhook {

    private final PayOSConfig payOSConfig;

    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody Webhook webhook) {
        PayOS payOS = payOSConfig.payOS();
        try {
            var data = payOS.webhooks().verify(webhook);
            System.out.println("Thanh toán thành công: " + data.getOrderCode());
            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            System.err.println("Webhook không hợp lệ: " + e.getMessage());
            return ResponseEntity.badRequest().body("Invalid webhook");
        }
    }
}
