package io.openstech.admin_hub.communication.client;

import io.openstech.admin_hub.domain.ProductDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;


@Controller
public class ProductClient {

    private final WebClient webClient;


    final String productEndpoint;

    public ProductClient(WebClient webClient, @Value("${product-endpoint}") String productEndpoint) {
        this.webClient = webClient;
        this.productEndpoint = productEndpoint;
    }


    public List<ProductDTO> getTrending(int page, int size) {

        return webClient.get()

                .uri(uriBuilder -> uriBuilder.path(productEndpoint)
                        .queryParam("page", page)
                        .queryParam("size", size)
                        .build())
                .retrieve()
                .bodyToFlux(ProductDTO.class)
                .collectList().block();
    }


    public ProductDTO searchProduct(String productNum) {
        return webClient.get().uri(productEndpoint + "/" + productNum).retrieve().bodyToMono(ProductDTO.class).block();
    }



    public ProductDTO insertProduct(ProductDTO productDTO) {
        return webClient.post().uri(productEndpoint)
                .body(Mono.just(productDTO), ProductDTO.class)
                .retrieve()
                .bodyToMono(ProductDTO.class).block();

    }



    public ProductDTO renewProduct(String productNum, ProductDTO productDTO) {
        productDTO.setProductNumber(productNum);
      return   webClient.put().uri(productEndpoint +"/"+productNum)
                        .body(Mono.just(productDTO),ProductDTO.class)
                                .retrieve()
                                        .bodyToMono(ProductDTO.class).block();

    }

    public ProductDTO modifyProduct(String productNum, ProductDTO productDTO) {

        return webClient.patch().uri(productEndpoint +"/"+productNum).body(Mono.just(productDTO),ProductDTO.class).retrieve().bodyToMono(ProductDTO.class).block();

    }


    public String removeProduct(String productNum) {

        webClient.delete().uri(productEndpoint +"/"+ productNum).retrieve().bodyToMono(ProductDTO.class).block();
        return "Product removed successfully";
    }



}
