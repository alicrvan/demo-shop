package io.techmo.product_managment.persistence.repository;

import io.techmo.product_managment.persistence.domain.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ProductReviewRepository extends JpaRepository<ProductReview,Long> {


}
