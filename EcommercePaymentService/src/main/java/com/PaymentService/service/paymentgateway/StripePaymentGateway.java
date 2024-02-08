package com.PaymentService.service.paymentgateway;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class StripePaymentGateway implements PaymentGateway {
    @Value("${stripe.key.secret}")
    private String stripeAPIKey; // Reading the Stripe API Key from the Environment Variable

    @Override
    public String generatePaymentLink(Long orderId, Long amount, String phoneNumber) throws StripeException {
        Stripe.apiKey = stripeAPIKey;

        Stripe.apiKey = "sk_test_51OgwmxSIFKNgaSgoJLzptM9o63Ce67iesevqIat4vcnr4c7lgKULhO95Dwo3YSTCkh2K5jjBQgxkJavDVeGePQWA00vqBhzTPM";

        PriceCreateParams priceParams = PriceCreateParams.builder()
                        .setCurrency("INR")
                        .setUnitAmount(amount)
                        /* .setRecurring(PriceCreateParams.Recurring.builder()
                                        .setInterval(PriceCreateParams.Recurring.Interval.MONTH).build()) */
                        .setProductData(PriceCreateParams.ProductData.builder()
                                        .setName("Gold Plan")
                                        .build())
                        .build();

        Price price = Price.create(priceParams);

        PaymentLinkCreateParams paymentLinkParams = PaymentLinkCreateParams.builder()
                .addLineItem(PaymentLinkCreateParams.LineItem.builder() // Mandatory Field - Line Item is an Object in which we can set the Data
                        .setPrice(price.getId()) // Required Field
                        .setQuantity(1L) // Required Field
                        .build())
                .build();

        PaymentLink paymentLink = PaymentLink.create(paymentLinkParams);
        return paymentLink.toString();
    }
}