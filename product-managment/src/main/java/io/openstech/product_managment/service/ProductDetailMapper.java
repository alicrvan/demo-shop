package io.openstech.product_managment.service;

import io.openstech.product_managment.communication.dto.ProductDetailDTO;
import io.openstech.product_managment.persistence.domain.ProductDetail;
import org.springframework.stereotype.Service;


@Service
public class ProductDetailMapper {



    public ProductDetail toProductDetail(ProductDetailDTO productDetailDTO){


        return new ProductDetail(

                productDetailDTO.getName(),
                productDetailDTO.getModel(),
                productDetailDTO.getWeight(),
                productDetailDTO.getDimension(),
                productDetailDTO.getItemsInsideBox());
    }


    public ProductDetailDTO toProductDetailDTO(ProductDetail productDetail){

        return new ProductDetailDTO(

                productDetail.getName(),
                productDetail.getModel(),
                productDetail.getWeight(),
                productDetail.getDimension(),
                productDetail.getInsideBox());
    }

}
