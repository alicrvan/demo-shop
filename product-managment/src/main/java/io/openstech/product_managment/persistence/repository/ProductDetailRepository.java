package io.openstech.product_managment.persistence.repository;

import io.openstech.product_managment.persistence.domain.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductDetailRepository extends JpaRepository<ProductDetail,Long> {
}
