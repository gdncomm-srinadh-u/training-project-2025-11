package com.major.Login.services;

public interface JwtServices {
    String generateToken(String customerEmail);

    String validateToken(String token);
}
