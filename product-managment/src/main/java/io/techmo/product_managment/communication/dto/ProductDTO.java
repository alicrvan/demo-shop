package io.techmo.product_managment.communication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.techmo.product_managment.persistence.domain.ProductCategory;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;


@EqualsAndHashCode
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class ProductDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String productNumber;

    @NotBlank
    private String title;
    @NotBlank
    private String description;

    @Digits(integer = 5, fraction = 2)
    @NotNull
    @DecimalMin(value = "0.01", message = "Price must be greater than 0.1")
    private BigDecimal price;

    @Min(1)
    private Integer quantity;

    @NotNull
    private Boolean inStock;
    @NotNull
    private Boolean shippingPossible;

    @NotNull
    private ProductCategory productCategory;

    @Valid
    @NotNull
    private ProductDetailDTO details;


    public ProductDTO(String title, String description, BigDecimal price, Integer quantity, Boolean inStock, Boolean shippingPossible, ProductCategory productCategory, ProductDetailDTO details) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.inStock = inStock;
        this.shippingPossible = shippingPossible;
        this.productCategory = productCategory;
        this.details = details;
    }

    public ProductDTO(String productNumber, String title, ProductDetailDTO details) {
        this.productNumber = productNumber;
        this.title = title;
        this.details = details;
    }

    public ProductDTO(String title, ProductDetailDTO details) {
        this.title = title;
        this.details = details;
    }
}
