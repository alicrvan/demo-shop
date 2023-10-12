package io.openstech.product_managment.communication.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.*;
import java.util.Set;
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDTO {

    @NotBlank
    private String name;
    @NotBlank
    private String model;
    @NotBlank
    private String weight;
    @NotBlank
    private String dimension;
    @NotEmpty
    private Set<@NotEmpty @Size(min = 1) String> itemsInsideBox =  new HashSet<>();


}
