package io.techmo.product_managment.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
@EqualsAndHashCode
@Setter
@Getter
@Entity
@NoArgsConstructor
public class ProductReview {
//TODO test this
   //@JsonIgnore
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String star;
    @NotBlank
    private String comment;

    public ProductReview(String star, String comment) {
        this.star = star;
        this.comment = comment;
    }


    //TODO add star

//    private  enum Star {
//        ONE("\u2605\u2606\u2606\u2606\u2606"),
//        TWO("\u2605\u2605\u2606\u2606\u2606"),
//        THREE("\u2605\u2605\u2605\u2606\u2606"),
//        FOUR("\u2605\u2605\u2605\u2605\u2606"),
//        FIVE("\u2605\u2605\u2605\u2605\u2605"),
//        NO_STAR("\u2606");
//
//        private String star;
//        Star(String star) {
//        }
//        public String getStar() {
//            return star;
//        }
//    }
}
