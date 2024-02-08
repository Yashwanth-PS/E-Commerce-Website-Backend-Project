package com.PaymentService.service.paymentgateway;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentGateway { // Adapter Design Pattern
    String generatePaymentLink(Long orderId, Long amount, String phoneNumber) throws RazorpayException, StripeException;
}