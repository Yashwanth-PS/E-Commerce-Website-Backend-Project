package com.project.EcommerceProductService.constants;

import org.springframework.beans.factory.annotation.Value;

public class ClientConstants { // Class of constants

    @Value("${fakestore.api.path.product}") // Field Injection
    private static String fakeStoreAPIProductPath;

    @Value("${fakestore.api.url}")
    private static String fakeStoreAPIURL;

    public static final String productURL = fakeStoreAPIURL + fakeStoreAPIProductPath;
}
