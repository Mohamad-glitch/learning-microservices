package com.pm.apigateway.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class JwtValidationGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {
    // this class will give us a custom logic to every endpoint we have in the gateway

    // this will be the request
    private final WebClient webClient;

    // i dont get it here
    public JwtValidationGatewayFilterFactory(WebClient.Builder webClientBuilder,
                                             @Value("${auth.service.url}") String authServiceUrl) {
        this.webClient = webClientBuilder.baseUrl(authServiceUrl).build();

    }

    @Override
    public GatewayFilter apply(Object config) {

        // exchange is a java variable that contains current request

        return (exchange, chain) ->{
            String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            if(token == null|| !token.startsWith("Bearer ")) {
                // this code will return not UNAUTHORIZED code
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

                // now we need to return the http code to the request
                return exchange.getResponse().setComplete();
            }

            // this will cause the request for the auth service /validate url
            return webClient.get()
                    .uri("/validate")
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .retrieve()
                    .toBodilessEntity()
                    .then(chain.filter(exchange));
        };

    }
}
