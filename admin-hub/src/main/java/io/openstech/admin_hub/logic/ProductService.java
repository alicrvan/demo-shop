package io.openstech.admin_hub.logic;

import io.openstech.admin_hub.communication.client.ProductController;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

private final ProductController productController;

//TODO make some method to apply discount
    public ProductService(ProductController productController) {
        this.productController = productController;
    }

//
//    public List<ProductDTO> getTopPicks() {
//        productController.getTrending();
//        return null;
//    }


}
