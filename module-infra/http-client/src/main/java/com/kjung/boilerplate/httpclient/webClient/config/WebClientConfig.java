package com.kjung.boilerplate.httpclient.webClient.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024)) // default 메모리 설정
                .filter(logRequest())
                .filter(handleErrors())
                .build();
    }

    private ExchangeFilterFunction logRequest() {
        return (clientRequest, next) -> {
            log.info("Request:{} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers()
                    .forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));

            return next.exchange(clientRequest);
        };
    }

    private ExchangeFilterFunction handleErrors() {
        return (clientRequest, next) -> next.exchange(clientRequest)
                .doOnNext(clientResponse -> {
                    if (clientResponse.statusCode().is4xxClientError()) {
                        log.error("Client Error: {}", clientResponse.statusCode());
                    } else if (clientResponse.statusCode().is5xxServerError()) {
                        log.error("Server Error: {}", clientResponse.statusCode());
                    }
                })
                .doOnError(error -> log.error("Error during request: {}", error.getMessage(), error));
    }

}