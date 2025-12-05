package com.major.Login.services;


import com.major.Login.dto.CustomerRegistrationDTO;
import com.major.Login.dto.LoginResponseDTO;

public interface AuthServices {
    String saveUser(CustomerRegistrationDTO customerRegistrationDTO);
    LoginResponseDTO generateToken(String customerEmail);

    LoginResponseDTO getUserByEmail(String email);

    String validateToken(String token);
}
