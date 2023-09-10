package io.openstech.admin_hub.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {


    private String productNumber;

    private String title;

    private String description;

    private BigDecimal price;

    private Integer quantity;

    private Boolean inStock;

    private Boolean shippingPossible;


    private ProductCategory productCategory;


    private ProductDetail details;
}
