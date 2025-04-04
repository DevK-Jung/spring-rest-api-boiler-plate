package com.kjung.boilerplate.httpclient.webClient;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
public class WebClientHelper {

    private final WebClient webClient;

    public <T> Mono<T> getMono(@NonNull String url,
                               HttpHeaders headers,
                               Map<String, ?> queryParams,
                               @NonNull Class<T> responseType) {

        return executeMonoRequest(url, HttpMethod.GET, null, headers, queryParams, responseType);
    }

    public <T> Mono<T> getMono(@NonNull String url,
                               HttpHeaders headers,
                               Map<String, ?> queryParams,
                               @NonNull ParameterizedTypeReference<T> responseType) {

        return executeMonoRequest(url, HttpMethod.GET, null, headers, queryParams, responseType);
    }

    public <T> Mono<T> postMono(@NonNull String url, Object body, HttpHeaders headers, Map<String, ?> queryParams, @NonNull Class<T> responseType) {
        return executeMonoRequest(url, HttpMethod.POST, body, headers, queryParams, responseType);
    }

    public <T> Mono<T> postMono(@NonNull String url, Object body, HttpHeaders headers, Map<String, ?> queryParams, @NonNull ParameterizedTypeReference<T> responseType) {
        return executeMonoRequest(url, HttpMethod.POST, body, headers, queryParams, responseType);
    }

    public <T> Mono<T> putMono(@NonNull String url, Object body, HttpHeaders headers, Map<String, ?> queryParams, @NonNull Class<T> responseType) {
        return executeMonoRequest(url, HttpMethod.PUT, body, headers, queryParams, responseType);
    }

    public <T> Mono<T> putMono(@NonNull String url, Object body, HttpHeaders headers, Map<String, ?> queryParams, @NonNull ParameterizedTypeReference<T> responseType) {
        return executeMonoRequest(url, HttpMethod.PUT, body, headers, queryParams, responseType);
    }

    public Mono<Void> deleteMono(@NonNull String url, HttpHeaders headers, Map<String, ?> queryParams) {
        return executeMonoRequest(url, HttpMethod.DELETE, null, headers, queryParams, Void.class);
    }


    public <T> Flux<T> getFlux(@NonNull String url,
                               HttpHeaders headers,
                               Map<String, ?> queryParams,
                               @NonNull Class<T> responseType) {

        return executeFluxRequest(url, HttpMethod.GET, null, headers, queryParams, responseType);
    }

    public <T> Flux<T> getFlux(@NonNull String url,
                               HttpHeaders headers,
                               Map<String, ?> queryParams,
                               @NonNull ParameterizedTypeReference<T> responseType) {

        return executeFluxRequest(url, HttpMethod.GET, null, headers, queryParams, responseType);
    }

    public <T> Flux<T> postFlux(@NonNull String url, Object body, HttpHeaders headers, Map<String, ?> queryParams, @NonNull Class<T> responseType) {
        return executeFluxRequest(url, HttpMethod.POST, body, headers, queryParams, responseType);
    }

    public <T> Flux<T> postFlux(@NonNull String url, Object body, HttpHeaders headers, Map<String, ?> queryParams, @NonNull ParameterizedTypeReference<T> responseType) {
        return executeFluxRequest(url, HttpMethod.POST, body, headers, queryParams, responseType);
    }

    public <T> Flux<T> putFlux(@NonNull String url, Object body, HttpHeaders headers, Map<String, ?> queryParams, @NonNull Class<T> responseType) {
        return executeFluxRequest(url, HttpMethod.PUT, body, headers, queryParams, responseType);
    }

    public <T> Flux<T> putFlux(@NonNull String url, Object body, HttpHeaders headers, Map<String, ?> queryParams, @NonNull ParameterizedTypeReference<T> responseType) {
        return executeFluxRequest(url, HttpMethod.PUT, body, headers, queryParams, responseType);
    }

    public Flux<Void> deleteFlux(@NonNull String url, HttpHeaders headers, Map<String, ?> queryParams) {
        return executeFluxRequest(url, HttpMethod.DELETE, null, headers, queryParams, Void.class);
    }

    private <T> Mono<T> executeMonoRequest(String url,
                                           HttpMethod method,
                                           Object body,
                                           HttpHeaders headers,
                                           Map<String, ?> queryParams,
                                           Class<T> responseType) {
        WebClient.ResponseSpec responseSpec = buildRequestSpec(url, method, body, headers, queryParams);
        return responseSpec.bodyToMono(responseType);
    }

    private <T> Mono<T> executeMonoRequest(String url,
                                           HttpMethod method,
                                           Object body,
                                           HttpHeaders headers,
                                           Map<String, ?> queryParams,
                                           ParameterizedTypeReference<T> responseType) {

        return buildRequestSpec(url, method, body, headers, queryParams)
                .bodyToMono(responseType);
    }

    private <T> Flux<T> executeFluxRequest(String url,
                                           HttpMethod method,
                                           Object body,
                                           HttpHeaders headers,
                                           Map<String, ?> queryParams,
                                           Class<T> responseType) {
        WebClient.ResponseSpec responseSpec = buildRequestSpec(url, method, body, headers, queryParams);
        return responseSpec.bodyToFlux(responseType);
    }

    private <T> Flux<T> executeFluxRequest(String url,
                                           HttpMethod method,
                                           Object body,
                                           HttpHeaders headers,
                                           Map<String, ?> queryParams,
                                           ParameterizedTypeReference<T> responseType) {

        return buildRequestSpec(url, method, body, headers, queryParams)
                .bodyToFlux(responseType);
    }

    private WebClient.ResponseSpec buildRequestSpec(String url,
                                                    HttpMethod method,
                                                    Object body,
                                                    HttpHeaders headers,
                                                    Map<String, ?> queryParams) {

        WebClient.RequestBodySpec requestSpec = webClient.method(method)
                .uri(buildUrlWithParams(url, queryParams))
                .accept(APPLICATION_JSON)
                .headers(httpHeaders -> applyHeaders(httpHeaders, headers));

        if (body != null) {
            requestSpec.bodyValue(body);
        }

        return requestSpec
                .retrieve();
    }

    private void applyHeaders(HttpHeaders targetHeaders, HttpHeaders sourceHeaders) {
        if (sourceHeaders != null) {
            targetHeaders.putAll(sourceHeaders);
        }
    }

    private String buildUrlWithParams(String url, Map<String, ?> queryParams) {
        if (queryParams == null) return url;

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(url);

        queryParams.forEach((key, value) -> {
            if (value == null) return;

            if (value instanceof List) {
                ((List<?>) value)
                        .forEach(listValue -> {
                            if (listValue == null) return;

                            uriBuilder.queryParam(key, listValue);
                        });

            } else {
                uriBuilder.queryParam(key, value);
            }
        });

        return uriBuilder
                .build()
                .toUriString();
    }
}
