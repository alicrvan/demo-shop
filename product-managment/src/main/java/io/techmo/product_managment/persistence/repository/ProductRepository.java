package io.techmo.product_managment.persistence.repository;

import io.techmo.product_managment.persistence.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByTitleOrProductDetailNameOrProductDetailModel(String title, String name, String model);

    boolean existsByProductNumber(String productNumber);

    Optional<Product> findProductByProductNumber(String productNumber);

    Optional<Product> deleteByProductNumber(String productNumber);


    //List<Product> findTopNByOrderByViewDesc(int limit);
}
