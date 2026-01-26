package com.xth.xthpayment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/payment-form")
public class PaymentFormController {

    @GetMapping("/form")
    public String paymentForm(Model model) {
        return "form-payment-test";
    }

    @RequestMapping("/vnPayReturn")
    public String vnPayReturn(Model model, @RequestParam("vnp_TransactionStatus") String vnp_TransactionStatus) {
        model.addAttribute("message", vnp_TransactionStatus.equals("00") ? "Success" : "Fail");
        return "form-payment-return";
    }
}
