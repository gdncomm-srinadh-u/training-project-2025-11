package com.major.Login.dto;
import lombok.Data;

@Data
public class AuthRequest {
    private String customerEmail;
    private String customerPassword;
}
