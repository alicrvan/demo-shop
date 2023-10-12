package io.openstech.product_managment.communication.endpoint;

import io.openstech.product_managment.communication.dto.PatchProductDTO;
import io.openstech.product_managment.service.ProductService;
import io.openstech.product_managment.communication.dto.ProductDTO;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductEndpoint {

    private final ProductService productService;

    public ProductEndpoint(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public List<ProductDTO> getAllProduct(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        return productService.returnAllProduct(page,size);
    }

    @GetMapping("/{productNumber}")
    public ProductDTO getProduct(@PathVariable String productNumber) {
        return productService.returnProduct(productNumber);
    }

    @PostMapping
    public ProductDTO addProduct(@RequestBody @Valid ProductDTO productDTO) {
        return productService.insertProduct(productDTO);
    }


    @PutMapping("/{productNumber}")
    public ProductDTO editProduct(@PathVariable String productNumber, @RequestBody @Valid ProductDTO productDTO) {
        return productService.modifyProduct(productNumber, productDTO);
    }


    @PatchMapping("/{productNumber}")
    public ProductDTO editProductField(@PathVariable String productNumber, @RequestBody @Valid PatchProductDTO patchProductDTO) {
        return productService.modifyProductField(productNumber, patchProductDTO);
    }


    @DeleteMapping("/{productNumber}")
    public void deleteProduct(@PathVariable String productNumber) {
         productService.removeProduct(productNumber);
    }

}
