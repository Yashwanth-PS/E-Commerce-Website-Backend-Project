package com.PaymentService.config;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorpayConfig {
    @Value("$(razorpay.key.id)")
    private String razorPayKeyID;

    @Value("$(razorpay.key.secret)")
    private String razorPayKeySecret;
    @Bean // Spring Automatically calls the Method and creates a Bean (Object) of the return type
    public RazorpayClient createRazorpayClient() throws RazorpayException {
        // Creating an Object of Razor Pay Client - By Passing the API Key and Secret Key
        return new RazorpayClient(razorPayKeyID, razorPayKeySecret);
    } // Spring makes sure that there is only one Object of RazorPay Client across the Project
}