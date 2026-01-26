package com.xth.xthpayment.config.payment;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@Configuration
public class VNPayConfig {

    @Getter
    @Value("${payment.vnPay.url}")
    private String vnp_PayUrl;

    @Getter
    @Value("${payment.vnPay.secretKey}")
    private String vnp_SecretKey;

    @Value("${payment.vnPay.version}")
    private String vnp_Version;
    @Value("${payment.vnPay.returnUrl}")
    private String vnp_ReturnUrl;
    @Value("${payment.vnPay.tmnCode}")
    private String vnp_TmnCode;
    @Value("${payment.vnPay.command}")
    private String vnp_Command;
    @Value("${payment.vnPay.orderType}")
    private String vnp_OrderType;

    public Map<String, String> getVNPayConfig(String vnp_TxnRef) {
        Map<String, String> vnpParams = new HashMap<>();
        vnpParams.put("vnp_Version", vnp_Version);
        vnpParams.put("vnp_Command", vnp_Command);
        vnpParams.put("vnp_TmnCode", vnp_TmnCode);
        vnpParams.put("vnp_CurrCode", "VND");
        vnpParams.put("vnp_TxnRef", vnp_TxnRef);
        vnpParams.put("vnp_OrderInfo", "Thanh toan don hang: " + vnp_TxnRef);
        vnpParams.put("vnp_OrderType", vnp_OrderType);
        vnpParams.put("vnp_Locale", "vn");
        vnpParams.put("vnp_ReturnUrl", vnp_ReturnUrl);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnpParams.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnpParams.put("vnp_ExpireDate", vnp_ExpireDate);

        return vnpParams;
    }
}
