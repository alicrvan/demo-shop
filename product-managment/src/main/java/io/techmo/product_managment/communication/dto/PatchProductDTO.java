package io.techmo.product_managment.communication.dto;

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
public class PatchProductDTO {


    @Size(min = 1)
    private String title;

    @Size(min = 1)
    private String description;

    @Digits(integer = 5, fraction = 2)
    @DecimalMin(value = "0.01", message = "Price must be greater than 0.1")
    private BigDecimal price;

    @Min(1)
    private Integer quantity;

    private Boolean available;

    private Boolean deliverable;


    private ProductCategory productCategory;

    @Valid
    private PatchProductDetailDTO productDetailDTO;


    public PatchProductDTO(String title) {
        this.title = title;
    }
}
