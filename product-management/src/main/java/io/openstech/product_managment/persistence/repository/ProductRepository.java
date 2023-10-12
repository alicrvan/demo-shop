package io.openstech.product_managment.persistence.repository;

import io.openstech.product_managment.persistence.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Product e " +
            "WHERE e.title = :title OR e.productDetail.name = :name OR e.productDetail.model = :model")
    boolean existsByTitleOrNameOrModel(
            @Param("title") String title,
            @Param("name") String name,
            @Param("model") String model
    );


    boolean existsByProductNumber(String productNumber);

    Optional<Product> findProductByProductNumber(String productNumber);

    void deleteByProductNumber(String productNumber);


    //List<Product> findTopNByOrderByViewDesc(int limit);
}
