package io.openstech.user_managment.persistence.domian;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
public class CustomerProfile {


    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Pattern(regexp="\\d{10}", message="Phone number must be 10 digits")
    private String phoneNumber;

    @Email
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;





}
