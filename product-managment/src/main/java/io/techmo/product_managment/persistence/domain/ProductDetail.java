package io.techmo.product_managment.persistence.domain;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.*;


@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@Entity
public class ProductDetail {


    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String name;
    @NotBlank
    @Column(unique = true)
    private String model;
    @NotBlank
    private String weight;
    @NotBlank
    private String dimension;
//TODO find a way to preserve insert order in a set @OrderColumn(name = "insertion_order")

    @NotEmpty
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<@NotEmpty @Size(min = 1) String> insideBox = new HashSet<>();

    public ProductDetail(String name, String model, String weight, String dimension, Set<String> insideBox) {

        this.name = name;
        this.model = model;
        this.weight = weight;
        this.dimension = dimension;
        this.insideBox = insideBox;
    }


}
