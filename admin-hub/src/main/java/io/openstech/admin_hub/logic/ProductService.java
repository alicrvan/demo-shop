package io.openstech.admin_hub.logic;

import io.openstech.admin_hub.communication.client.ProductClient;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

private final ProductClient productClient;

//TODO make some method to apply discount
    public ProductService(ProductClient productClient) {
        this.productClient = productClient;
    }

//
//    public List<ProductDTO> getTopPicks() {
//        productController.getTrending();
//        return null;
//    }


}
