package io.openstech.user_managment.communication.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


public class AddressDTO {

    @Min(1000)
    @Max(9999)
    private String postalCode;

    @NotBlank
    private String street;

    @NotBlank
    private String city;

}
