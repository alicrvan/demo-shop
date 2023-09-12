package io.openstech.admin_hub.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class ProductConfiguration {

    private final String productUrl;

    public ProductConfiguration(@Value("${product-url}") String productUrl) {
        this.productUrl = productUrl;
    }

    @Bean
    WebClient webClient(){
         return WebClient.create(productUrl);

//          return      webClientBuilder.baseUrl("http://localhost:9001").build();


//        WebClient client = WebClient.builder()
//                .baseUrl("http://localhost:8080")
//                .defaultCookie("cookieKey", "cookieValue")
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
//                .build();
    }
}
