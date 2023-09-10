package io.openstech.admin_hub.logic;

import io.openstech.admin_hub.communication.ProductController;
import io.openstech.admin_hub.persistence.domain.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {

private final ProductController productController;


    public ProductService(ProductController productController) {
        this.productController = productController;
    }

//
//    public List<ProductDTO> getTopPicks() {
//        productController.getTrending();
//        return null;
//    }


}
