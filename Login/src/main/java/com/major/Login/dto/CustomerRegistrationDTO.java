package com.major.Login.dto;
import com.major.Login.Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CustomerRegistrationDTO {
    private String customerName;
    @NotBlank(message =Constants.Email_cant_be_empty)
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$",
            message =Constants.Email_domain_must_be
    )
    private String customerEmail;
    private String customerPassword;
    @NotBlank(message = Constants.MOBILE_NUMBER_SHOULD_NOT_BE_EMPTY)
    @Pattern(regexp = "^[0-9]{10}$", message =Constants.MOBILE_NUMBER_SHOULD_BE_10_NUMBERS)
    private String phoneNo;
    private String address;
}
