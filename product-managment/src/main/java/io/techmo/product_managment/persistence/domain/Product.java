package io.techmo.product_managment.persistence.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@EqualsAndHashCode
@Setter
@Getter
@NoArgsConstructor
@Validated
@Entity
public class Product {
    //TODO add image later on


    @Setter(AccessLevel.PRIVATE)
    @Getter(AccessLevel.PRIVATE)
    @Id
    @GeneratedValue
    private Long id;


    @NotEmpty
    @Column(unique = true)
    private String productNumber;

    @NotBlank
    @Column(unique = true)
    private String title;
    @NotBlank
    private String description;
//TODO you can still use 1.2 with one fraction enforce only two fraction in insomnia
    @Digits(integer = 5,fraction = 2)
    @NotNull
    @DecimalMin(value = "0.01", message = "Price must be equal or greater than 0.1")
    private BigDecimal price;

    @Min(1)
    private Integer quantity;
    @NotNull
    private Boolean available;
    @NotNull
    private Boolean deliverable;
//TODO implement views counting
    private int views;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    @Valid
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private ProductDetail productDetail;

    @Valid
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<ProductReview> productReviews = new HashSet<>();


    public Product( String title, String description, BigDecimal price, Integer quantity, Boolean available, Boolean deliverable, ProductCategory productCategory, ProductDetail productDetail) {

        this.title = title;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.available = available;
        this.deliverable = deliverable;
        this.productCategory = productCategory;
        this.productDetail = productDetail;
    }

    public Product(String productNumber, String title) {
        this.productNumber = productNumber;
        this.title = title;
    }
}
