package com.PaymentService.service;

import com.PaymentService.dto.InitiatePaymentRequestDTO;
import com.PaymentService.service.paymentgateway.PaymentGateway;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PaymentService {
    private PaymentGateway paymentGateway;

    public PaymentService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public String initializePayment(Long orderId, Long amount, String phoneNumber) throws RazorpayException, StripeException {
        // Order order = orderService.getOrderDetails(orderId());
        // We need to call the Payment Gateway to Generate the Payment Link
        // Long amount = 1025L;
        return paymentGateway.generatePaymentLink(orderId, amount, phoneNumber);
    }
}