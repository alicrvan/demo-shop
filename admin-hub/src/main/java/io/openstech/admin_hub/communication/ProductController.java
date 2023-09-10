package io.openstech.admin_hub.communication;

import io.openstech.admin_hub.persistence.domain.ProductDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@Controller
public class ProductController {
    private final RestTemplate restTemplate;


    String url = "http://localhost:8080/products";

    public ProductController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<ProductDTO> getTrending(int page, int size) {
        ProductDTO[] r = restTemplate.getForObject(url + "?" + "page="+page + "&"+"size="+size, ProductDTO[].class);
        return Arrays.asList(r);

    }


    public ProductDTO searchProduct(String productNum) {

        return restTemplate.getForObject(url + "/" + productNum, ProductDTO.class);
    }

    public ProductDTO insertProduct(ProductDTO productDTO) {

        return restTemplate.postForObject(url,productDTO, ProductDTO.class);
    }



    public ProductDTO renewProduct(String productNum, ProductDTO productDTO) {
        restTemplate.put(url + "/" + productNum, productDTO);
        productDTO.setProductNumber(productNum);
        return productDTO;
    }



    public ProductDTO modifyProduct(String productNum, ProductDTO productDTO) {

        return restTemplate.patchForObject(url + "/" + productNum, productDTO , ProductDTO.class);
    }



    public String removeProduct(String productNum) {

         restTemplate.delete(url + "/" + productNum, ProductDTO.class);
        return "Product removed successfully";
    }



}
