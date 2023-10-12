package io.openstech.product_managment.persistence.repository;

import io.openstech.product_managment.persistence.domain.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductReviewRepository extends JpaRepository<ProductReview,Long> {


}
