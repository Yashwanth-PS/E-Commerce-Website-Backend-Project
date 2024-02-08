package com.PaymentService.service.paymentgateway;

import com.razorpay.PaymentLink;
import org.json.JSONObject;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.stereotype.Service;

@Service
public class RazorpayPaymentGateway implements PaymentGateway {
    private RazorpayClient razorpayClient;

    public RazorpayPaymentGateway(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @Override
    public String generatePaymentLink(Long orderId, Long amount, String phoneNumber) throws RazorpayException {

        // Creating an Object of Razor Pay Client - By Passing the API Key and Secret Key
        // RazorpayClient razorpay = new RazorpayClient("[YOUR_KEY_ID]", "[YOUR_KEY_SECRET]");

        // Creating a Payment Link Request
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",amount);
        paymentLinkRequest.put("currency","INR");
        // paymentLinkRequest.put("accept_partial",true);
        // paymentLinkRequest.put("first_min_partial_amount",100);
        // Expiry time of the Payment Link that will be generated
        paymentLinkRequest.put("expire_by",1707489773); // Epoch Time
        paymentLinkRequest.put("reference_id","PSY1989");
        paymentLinkRequest.put("description","Payment for policy no #23456");

        // Nested JSON Object to Add the Customer Details
        JSONObject customer = new JSONObject();
        customer.put("name","Yash Bro");
        customer.put("contact",phoneNumber);
        customer.put("email","yash@example.com");
        paymentLinkRequest.put("customer",customer);

        // Nested JSON Object to Add the Notification Details
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);

        // Nested JSON Object to Add the Notes Details
        JSONObject notes = new JSONObject();
        notes.put("policy_name","Jeevan Bima");
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://example-callback-url.com/");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
        return payment.toString();
    }
}