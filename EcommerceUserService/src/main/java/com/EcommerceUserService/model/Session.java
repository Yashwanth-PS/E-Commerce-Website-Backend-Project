package com.EcommerceUserService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Session extends BaseModel {
    private String token; // 30 Characters - 30 Bytes
    private Date expiringAt; // Double - 8 Bytes
    private Date loginAt; // Double - 8 Bytes
    @ManyToOne
    private User user; // User ID - Double - 8 Bytes
    @Enumerated(EnumType.ORDINAL) // Create a table for Enum
    private SessionStatus sessionStatus; // 10 Characters - 10 Bytes
    // 70 Bytes Per Session
    // --> 1 Million Logins --> 70 Mega Bytes
    // --> 1 Billion Logins --> 70 Giga Bytes
}