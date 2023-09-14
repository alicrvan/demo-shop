package io.openstech.admin_hub.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Configuration
public class ProductConfiguration {

    private final String productUrl;

    public ProductConfiguration(@Value("${product-url}") String productUrl) {
        this.productUrl = productUrl;
    }

    @Bean
    WebClient webClient(){
         return WebClient.create(productUrl);



//        public static ExchangeFilterFunction errorHandler() {
//            return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
//                if (clientResponse.statusCode().is5xxServerError()) {
//                    return clientResponse.bodyToMono(String.class)
//                            .flatMap(errorBody -> Mono.error(new UserDefinedException1(errorBody)));
//                } else if (clientResponse.statusCode().is4xxClientError()) {
//                    return clientResponse.bodyToMono(String.class)
//                            .flatMap(errorBody -> Mono.error(new UserDefinedException2(errorBody)));
//                } else {
//                    return Mono.just(clientResponse);
//                }
//            });
//        }

//          return      webClientBuilder.baseUrl("http://localhost:9001").build();


//        WebClient client = WebClient.builder()
//                .baseUrl("http://localhost:8080")
//                .defaultCookie("cookieKey", "cookieValue")
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
//                .build();
    }
}
