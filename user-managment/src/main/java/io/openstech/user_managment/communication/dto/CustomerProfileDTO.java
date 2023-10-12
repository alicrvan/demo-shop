package io.openstech.user_managment.communication.dto;


import io.openstech.user_managment.persistence.domian.Address;
import org.springframework.validation.annotation.Validated;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Validated
public class CustomerProfileDTO {


    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phoneNumber;

    @Email
    private String email;

    @Valid
    private Address address;
}
