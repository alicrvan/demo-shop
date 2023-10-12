package io.openstech.product_managment.communication.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatchProductDetailDTO {


    @Size(min = 1)
    private String name;
    @Size(min = 1)
    private String model;
    @Size(min = 1)
    private String weight;
    @Size(min = 1)
    private String dimension;

    @NotEmpty
    private Set<@NotEmpty @Size(min = 1) String> itemInsideBox = new HashSet<>();
}
