package com.EcommerceUserService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailDTO { // Information that the consumer would require to send an email to the user
    private String from;
    private String to;
    private String subject;
    private String body;
}
