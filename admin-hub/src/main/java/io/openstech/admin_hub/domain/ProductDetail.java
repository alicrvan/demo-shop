package io.openstech.admin_hub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetail {

    private String name;

    private String model;

    private String weight;

    private String dimension;

    private Set<String> itemsInsideBox;
}
