package io.openstech.admin_hub.communication.endpoint;

import io.openstech.admin_hub.communication.client.ProductClient;
import io.openstech.admin_hub.domain.ProductDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/dashboard/product-management")
public class ProductEndpoint {

    private final ProductClient productClient;

    public ProductEndpoint(ProductClient productClient) {
        this.productClient = productClient;
    }


    @GetMapping
    public List<ProductDTO> getAllProduct(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        return productClient.getTrending(page,size);
    }



    @GetMapping("/{productNumber}")
    public ProductDTO findProduct(@PathVariable String productNumber) {
        return productClient.searchProduct( productNumber);
    }




    @PostMapping
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        return productClient.insertProduct(productDTO);
    }


    @PutMapping("/{productNumber}")
    public ProductDTO updateProduct(@PathVariable String productNumber, @RequestBody ProductDTO productDTO) {
        return productClient.renewProduct(productNumber, productDTO);
    }


    @PatchMapping("/{productNumber}")
    public ProductDTO editProduct(@PathVariable String productNumber,@RequestBody  ProductDTO patchProductDTO) {
        return productClient.modifyProduct(productNumber, patchProductDTO);
    }


    @DeleteMapping("/{productNumber}")
    public String deleteProduct(@PathVariable String productNumber) {
        return productClient.removeProduct(productNumber);
    }



}
