package com.PaymentService.controller;

import com.PaymentService.dto.InitiatePaymentRequestDTO;
import com.PaymentService.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/")
    public String initializePayment(@RequestBody InitiatePaymentRequestDTO paymentRequestDTO) throws Exception {
        return paymentService.initializePayment(paymentRequestDTO.getOrderId(),
                paymentRequestDTO.getAmount(),
                paymentRequestDTO.getPhoneNumber());
    } //POST: http://localhost:8383/payment/
    /* {
        "orderId": "123456",
        "amount": "1000",
        "phoneNumber": "9876543210"
    } */
}