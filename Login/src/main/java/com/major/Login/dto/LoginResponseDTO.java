package com.major.Login.dto;

import com.major.Login.Constants;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    private int customerId;
    private String customerName;
    @Column(nullable = false, unique = true)
    @NotBlank(message = Constants.Email_cant_be_empty)
    private String customerEmail;
    @NotBlank(message = Constants.Password_Cant_be_empty)
    private String customerPassword;
    private String phoneNo;
    private String address;
    private String token;

}
