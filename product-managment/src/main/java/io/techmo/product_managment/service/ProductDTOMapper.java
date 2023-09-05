package io.techmo.product_managment.service;

import io.techmo.product_managment.communication.dto.ProductDTO;
import io.techmo.product_managment.persistence.domain.Product;
import org.springframework.stereotype.Service;


@Service
public class ProductDTOMapper {


private final ProductDetailMapper productDetailMapper;

    public ProductDTOMapper(ProductDetailMapper productDetailMapper) {
        this.productDetailMapper = productDetailMapper;

    }

    public Product toProduct(ProductDTO productDTO){

        return new Product(
                productDTO.getTitle(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                productDTO.getQuantity(),
                productDTO.getInStock(),
                productDTO.getShippingPossible(),
                productDTO.getProductCategory(),
                productDetailMapper.toProductDetail(productDTO.getDetails()));

    }


    public ProductDTO toProductDto(Product product){

        return new ProductDTO(
                product.getProductNumber(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getAvailable(),
                product.getDeliverable(),
                product.getProductCategory(),
                productDetailMapper.toProductDetailDTO(product.getProductDetail()));

    }

}


