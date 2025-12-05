package com.major.Login.Controller;

import com.major.Login.Constants;
import com.major.Login.dto.AuthRequest;
import com.major.Login.dto.CustomerRegistrationDTO;
import com.major.Login.dto.GenericResponse;
import com.major.Login.dto.LoginResponseDTO;
import com.major.Login.exception.CustomException;
import com.major.Login.services.AuthServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthServices service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public GenericResponse<String> addNewUser(@RequestBody CustomerRegistrationDTO customerRegistrationDTO) {
        String result = service.saveUser(customerRegistrationDTO);
        return new GenericResponse<>(
                true,
                Constants.Registration_done,
                result,
                LocalDateTime.now()
        );
    }

    @PostMapping("/login")
    public GenericResponse<LoginResponseDTO> getToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getCustomerEmail(),
                        authRequest.getCustomerPassword()
                )
        );

        if (authentication.isAuthenticated()) {
            LoginResponseDTO loginResponseDTO = service.generateToken(authRequest.getCustomerEmail());

            return new GenericResponse<>(
                    true,
                    Constants.Login_done,
                    loginResponseDTO,
                    LocalDateTime.now()
            );
        } else {
            throw new CustomException(Constants.Invalid_Mail_or_PWD);
        }
    }

    @GetMapping("/customer/{customerEmail}")
    public GenericResponse<LoginResponseDTO> getUserByEmail(@PathVariable String customerEmail) {
        LoginResponseDTO customer = service.getUserByEmail(customerEmail);

        return new GenericResponse<>(
                true,
                Constants.Customer_Fetched,
                customer,
                LocalDateTime.now()
        );
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam String token) {
        String customerEmail = service.validateToken(token);
        log.info(customerEmail);
        return ResponseEntity.ok(customerEmail);
    }
}
