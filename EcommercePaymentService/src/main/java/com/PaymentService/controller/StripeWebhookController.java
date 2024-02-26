package com.PaymentService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stripeWebhook")
public class StripeWebhookController {
    @PostMapping("/request")
    public void webhookRequest() {
        System.out.println("Stripe Webhook Request Received");
    }
}
