package io.openstech.user_managment.persistence.domian;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@Entity
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    @Min(1000)
    @Max(9999)
    private String postalCode;

    @NotBlank
    private String street;

    @NotBlank
    private String city;

}
