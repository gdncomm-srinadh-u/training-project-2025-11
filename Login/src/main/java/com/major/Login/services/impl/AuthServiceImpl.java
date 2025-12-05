package com.major.Login.services.impl;

import com.major.Login.Constants;
import com.major.Login.Entity.Customer;
import com.major.Login.dto.CustomerRegistrationDTO;
import com.major.Login.dto.LoginResponseDTO;
import com.major.Login.exception.CustomException;
import com.major.Login.repositories.CustomerRepositories;
import com.major.Login.services.AuthServices;
import com.major.Login.services.JwtServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthServices {
    @Autowired
    private CustomerRepositories customerRepositories;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtServices jwtService;

    @Override
    public String saveUser(CustomerRegistrationDTO customerRegistrationDTO) {
        Customer existingCustomer = customerRepositories.findByCustomerEmail(customerRegistrationDTO.getCustomerEmail());

        String PhoneNo = customerRegistrationDTO.getPhoneNo();
        String email = customerRegistrationDTO.getCustomerEmail();


        if (PhoneNo == null || PhoneNo.length() != 10 || !PhoneNo.matches("\\d{10}")) {
            throw new CustomException(Constants.MOBILE_NUMBER_SHOULD_BE_10_NUMBERS);
        }
        if (!email.toLowerCase().endsWith("@gmail.com")) {
            throw new CustomException(Constants.Email_Structure);
        }

        if (existingCustomer != null) {
            throw new CustomException(Constants.CUSTOMER_WITH_THIS_MAIL_EXISTS);
        }

        Customer newCustomer = new Customer();
        BeanUtils.copyProperties(customerRegistrationDTO, newCustomer);
        newCustomer.setCustomerPassword(passwordEncoder.encode(customerRegistrationDTO.getCustomerPassword()));

        customerRepositories.save(newCustomer);
        return Constants.USER_ADDED;
    }

    public LoginResponseDTO generateToken(String customerEmail) {
        String token=jwtService.generateToken(customerEmail);
        LoginResponseDTO loginResponseDTO=new LoginResponseDTO();
        Customer customer=customerRepositories.findByCustomerEmail(customerEmail);
        loginResponseDTO.setToken(token);
        BeanUtils.copyProperties(customer,loginResponseDTO);
        return loginResponseDTO;
    }
    @Override
    public LoginResponseDTO getUserByEmail(String email) {
        Customer customer=customerRepositories.findByCustomerEmail(email);
        LoginResponseDTO loginResponseDTO=new LoginResponseDTO();
        BeanUtils.copyProperties(customer,loginResponseDTO);
        return loginResponseDTO;
    }

    @Override
    public String validateToken(String token) {
        return jwtService.validateToken(token);
    }

}
