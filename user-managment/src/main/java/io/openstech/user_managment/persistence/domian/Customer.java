package io.openstech.user_managment.persistence.domian;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String username;


    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
    message = "8 characters long and contains both lowercase and uppercase letters, one digit")
    private String password;

    //@Column(columnDefinition = "VARCHAR(255) DEFAULT 'ROLE_USER'")
    final private String ROLE = "ROLE_CUSTOMER";

    @OneToOne(cascade = CascadeType.ALL)
    CustomerProfile customerProfile;

}
