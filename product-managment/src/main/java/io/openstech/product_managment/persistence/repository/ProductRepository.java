package io.openstech.product_managment.persistence.repository;

import io.openstech.product_managment.persistence.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {

//TODO use custom query to make the method name shorter
    boolean existsByTitleOrProductDetailNameOrProductDetailModel(String title, String name, String model);


    boolean existsByProductNumber(String productNumber);

    Optional<Product> findProductByProductNumber(String productNumber);

    void deleteByProductNumber(String productNumber);


    //List<Product> findTopNByOrderByViewDesc(int limit);
}
