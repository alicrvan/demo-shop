package io.openstech.user_managment.communication.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
@Setter
@Getter
@Validated
public class CustomerDTO {


    @NotBlank
    private String username;


    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "8 characters long and contains both lowercase and uppercase letters, one digit")
    private String password;

    @Valid
    CustomerProfileDTO customerProfileDTO;
}
