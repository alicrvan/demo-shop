package io.openstech.product_managment.service;

import io.openstech.product_managment.communication.dto.PatchProductDTO;
import io.openstech.product_managment.excpetion.ProductAlreadyExistException;
import io.openstech.product_managment.communication.dto.ProductDTO;
import io.openstech.product_managment.communication.dto.ProductDetailDTO;
import io.openstech.product_managment.persistence.domain.Product;
import io.openstech.product_managment.persistence.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.web.SecurityFilterChain;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @MockBean
    SecurityFilterChain securityFilterChain;

    @MockBean
    ProductRepository productRepository;

    @MockBean
    ProductDTOMapper productDTOMapper;


    @Spy
    ModelMapper modelMapper;

    String productNum = "FHX8SVHJW46P";

    ProductDTO productDTO;

    ProductDTO productDTOWithNum;
    ProductDetailDTO productDetailDTO;
    Product product;

    @BeforeEach
    void init() {

        productDetailDTO = new ProductDetailDTO("rx 480", "navi 11", "1 kg", "15x1x3mm", new HashSet<>(Set.of("GPU,HDMI")));

        productDTO = new ProductDTO("mockTitle", productDetailDTO);

        productDTOWithNum = new ProductDTO(productNum, productDetailDTO);

        product = new Product(productNum, "mockTitle");
    }


    @Test
    void returnAllProduct() {

        Page<Product> pageOfProducts = new PageImpl<>(List.of(product));

        Mockito.when(productRepository.findAll(PageRequest.of(0, 1))).thenReturn(pageOfProducts);


        List<ProductDTO> result = productService.returnAllProduct(0, 1);


        assertEquals(pageOfProducts.getContent().size(), result.size());


        Mockito.verify(productDTOMapper, times(pageOfProducts.getContent().size())).toProductDto(any(Product.class));

    }

    @Test
    void returnProduct() {


        Mockito.when(productRepository.findProductByProductNumber(productNum)).thenReturn(Optional.of(product));

        Mockito.when(productDTOMapper.toProductDto(product)).thenReturn(productDTO);

        productService.returnProduct(productNum);

        Mockito.verify(productDTOMapper).toProductDto(product);
    }


    @Test
    void insertProduct() {

        Mockito.when(productDTOMapper.toProduct(productDTO)).thenReturn(product);

        Mockito.when(productRepository.existsByTitleOrProductDetailNameOrProductDetailModel(anyString(), anyString(), anyString())).thenReturn(false);

        Mockito.when(productRepository.existsByProductNumber(productNum)).thenReturn(false);


        Mockito.when(productDTOMapper.toProductDto(product)).thenReturn(productDTOWithNum);

        ProductDTO result = productService.insertProduct(productDTO);

        assertEquals(productDTOWithNum, result);


        Mockito.verify(productRepository).existsByTitleOrProductDetailNameOrProductDetailModel("mockTitle", "rx 480", "navi 11");

        Mockito.verify(productDTOMapper).toProduct(productDTO);
        Mockito.verify(productDTOMapper).toProductDto(product);

        Mockito.verify(productRepository).save(product);

    }

    @Test
    void insertProductExist() {

        Mockito.when(productRepository.existsByTitleOrProductDetailNameOrProductDetailModel("mockTitle", "rx 480", "navi 11")).thenReturn(true);


        assertThrows(ProductAlreadyExistException.class, () -> productService.insertProduct(productDTO));

        Mockito.verify(productRepository).existsByTitleOrProductDetailNameOrProductDetailModel("mockTitle", "rx 480", "navi 11");

    }


    @Test
    void modifyProduct() {

        product.setProductNumber(productNum);

        Mockito.when(productRepository.findProductByProductNumber(productNum)).thenReturn(Optional.of(product));

        product.setTitle("intel arc");
        productDTOWithNum.setTitle("intel arc");
        Mockito.when(productDTOMapper.toProductDto(product)).thenReturn(productDTOWithNum);

        ProductDTO result = productService.modifyProduct(productNum, productDTO);

        assertEquals(productDTOWithNum, result);

        Mockito.verify(productRepository).save(product);
        Mockito.verify(productDTOMapper).toProductDto(product);
    }

    @Test
    void modifyProductField() {

        PatchProductDTO patchProductDTO = new PatchProductDTO("new Title");
        product.setProductNumber(productNum);
        Mockito.when(productRepository.findProductByProductNumber(productNum)).thenReturn(Optional.of(product));

        productDTOWithNum.setTitle(patchProductDTO.getTitle());

        Mockito.when(productDTOMapper.toProductDto(product)).thenReturn(productDTOWithNum);


        productService.modifyProductField(productNum, patchProductDTO);


        Mockito.verify(productDTOMapper).toProductDto(product);
        Mockito.verify(productRepository).save(product);

    }

    @Test
    void removeProduct() {


        Mockito.when(productRepository.findProductByProductNumber(productNum))
                        .thenReturn(Optional.of(product));
        Mockito.doNothing().when(productRepository).deleteByProductNumber(productNum);


       productService.removeProduct(productNum);


        Mockito.verify(productRepository).deleteByProductNumber(productNum);
    }
}