package io.techmo.product_managment.communication.endpoint;

import io.techmo.product_managment.communication.dto.PatchProductDTO;
import io.techmo.product_managment.communication.dto.ProductDTO;
import io.techmo.product_managment.communication.dto.ProductDetailDTO;
import io.techmo.product_managment.persistence.domain.ProductCategory;
import io.techmo.product_managment.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.client.RestClientException;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductEndpointTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    ProductService productService;

    String url = "/products";

    String productNum = "DBNX8SYMWPNL";

    ProductDetailDTO productDetailDTO;

    ProductDTO productDTO ;

    @BeforeEach
    void init() {

        productDetailDTO = new ProductDetailDTO("rx 480", "navi 11", "1 kg",
                "15x1x3mm", Set.of("GPU,HDMI"));

        productDTO = new ProductDTO("title", "description", BigDecimal.ONE,
                2, true, true, ProductCategory.COMPUTER, productDetailDTO);
    }


    @Test
    void getAllProduct() {
        int page = 0;
        int size = 2;

        testRestTemplate.getForObject(url + "?page=" + page + "&size=" + size, ProductDTO[].class);

        Mockito.verify(productService).returnAllProduct(0, 2);
    }

    @Test
    void getProduct() {


        testRestTemplate.getForObject(url + "/" + productNum, ProductDTO.class);

        Mockito.verify(productService).returnProduct(productNum);
    }


    @Test
    void addProduct() {

        testRestTemplate.postForObject(url, productDTO, ProductDTO.class);

        Mockito.verify(productService).insertProduct(productDTO);


    }


    @Test
    void addProductEmpty() {


        productDTO.setTitle("");

        assertThrows(RestClientException.class, () -> testRestTemplate
                .postForObject(url, productDTO, ProductDTO.class));

        Mockito.verifyNoInteractions(productService);


    }


    @Test
    void editProduct() {


        testRestTemplate.put(url + "/" + productNum, productDTO);

        Mockito.verify(productService).modifyProduct(productNum, productDTO);

    }

    @Test
    void editProductField() {

        PatchProductDTO p1 = new PatchProductDTO();
        testRestTemplate.patchForObject(url + "/" + productNum, p1, ProductDTO.class);

        Mockito.verify(productService).modifyProductField(eq(productNum), any(PatchProductDTO.class));

    }


    @Test
    void deleteProduct() {


        testRestTemplate.delete(url + "/" + productNum);

        Mockito.verify(productService).removeProduct(productNum);
    }
}