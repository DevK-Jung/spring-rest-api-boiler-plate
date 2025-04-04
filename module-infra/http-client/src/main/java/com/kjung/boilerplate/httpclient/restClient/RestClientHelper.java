package com.kjung.boilerplate.httpclient.restClient;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@Component
@RequiredArgsConstructor
public class RestClientHelper {

    private final RestClient restClient;

    public RestClientHelper() throws NoSuchAlgorithmException, KeyManagementException {
        ignoreSsl(); // todo 필요한 경우에만 세팅하도록 수정

        this.restClient = RestClient.builder()
                .requestFactory(new SimpleClientHttpRequestFactory())
                .build();
    }

    // todo 필요한 경우에만 세팅하도록 수정
    private void ignoreSsl() throws NoSuchAlgorithmException, KeyManagementException {
        // SSLContext 생성 및 신뢰 매니저 설정
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }
        }, new SecureRandom());

        // HttpsURLConnection에 SSL 컨텍스트 적용
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true); // 호스트 이름 검증 비활성화
    }

    /**
     * GET
     *
     * @param url          요청 URL
     * @param headers      헤더
     * @param queryParams  쿼리 파라미터
     * @param responseType 응답 타입
     * @param <T>          응답 객체 타입
     * @return 응답 데이터
     */
    public <T> T get(@NonNull String url,
                     HttpHeaders headers,
                     Map<String, ?> queryParams,
                     @NonNull Class<T> responseType) {

        return executeRequest(url, HttpMethod.GET, null, headers, queryParams, responseType);
    }

    /**
     * GET
     *
     * @param url          요청 URL
     * @param headers      헤더
     * @param queryParams  쿼리 파라미터
     * @param responseType 응답 타입
     * @param <T>          응답 객체 타입
     * @return 응답 데이터
     */
    public <T> T get(@NonNull String url,
                     HttpHeaders headers,
                     Map<String, ?> queryParams,
                     @NonNull ParameterizedTypeReference<T> responseType) {

        return executeRequest(url, HttpMethod.GET, null, headers, queryParams, responseType);
    }

    /**
     * POST
     *
     * @param url          요청 URL
     * @param headers      헤더
     * @param body         요청 데이터
     * @param responseType 응답 타입
     * @param <T>          응답 객체 타입
     * @return 응답 데이터
     */
    public <T> T post(@NonNull String url,
                      HttpHeaders headers,
                      @NonNull Object body,
                      @NonNull Class<T> responseType) {
        return executeRequest(url, HttpMethod.POST, body, headers, null, responseType);
    }

    /**
     * POST
     *
     * @param url          요청 URL
     * @param headers      헤더
     * @param body         요청 데이터
     * @param responseType 응답 타입
     * @param <T>          응답 객체 타입
     * @return 응답 데이터
     */
    public <T> T post(@NonNull String url,
                      HttpHeaders headers,
                      @NonNull Object body,
                      @NonNull ParameterizedTypeReference<T> responseType) {
        return executeRequest(url, HttpMethod.POST, body, headers, null, responseType);
    }


    /**
     * PUT
     *
     * @param url          요청 URL
     * @param headers      헤더
     * @param body         요청 데이터
     * @param responseType 응답 타입
     * @param <T>          응답 객체 타입
     * @return 응답 데이터
     */
    public <T> T put(@NonNull String url,
                     HttpHeaders headers,
                     @NonNull Object body,
                     @NonNull Class<T> responseType) {

        return executeRequest(url, HttpMethod.PUT, body, headers, null, responseType);
    }

    /**
     * PUT
     *
     * @param url          요청 URL
     * @param headers      헤더
     * @param body         요청 데이터
     * @param responseType 응답 타입
     * @param <T>          응답 객체 타입
     * @return 응답 데이터
     */
    public <T> T put(@NonNull String url,
                     HttpHeaders headers,
                     @NonNull Object body,
                     @NonNull ParameterizedTypeReference<T> responseType) {

        return executeRequest(url, HttpMethod.PUT, body, headers, null, responseType);
    }


    /**
     * DELETE
     *
     * @param url          요청 URL
     * @param headers      헤더
     * @param body         요청 데이터
     * @param responseType 응답 타입
     * @param <T>          응답 객체 타입
     * @return 응답 데이터
     */
    public <T> T delete(@NonNull String url,
                        HttpHeaders headers,
                        Object body,
                        @NonNull Class<T> responseType) {

        return executeRequest(url, HttpMethod.DELETE, body, headers, null, responseType);
    }

    /**
     * DELETE
     *
     * @param url          요청 URL
     * @param headers      헤더
     * @param body         요청 데이터
     * @param responseType 응답 타입
     * @param <T>          응답 객체 타입
     * @return 응답 데이터
     */
    public <T> T delete(@NonNull String url,
                        HttpHeaders headers,
                        Object body,
                        @NonNull ParameterizedTypeReference<T> responseType) {

        return executeRequest(url, HttpMethod.DELETE, body, headers, null, responseType);
    }

    private <T> T executeRequest(String url,
                                 HttpMethod method,
                                 Object body,
                                 HttpHeaders headers,
                                 Map<String, ?> queryParams,
                                 Class<T> responseType) {

        return buildRequestSpec(url, method, body, headers, queryParams)
                .body(responseType);
    }

    private <T> T executeRequest(String url,
                                 HttpMethod method,
                                 Object body,
                                 HttpHeaders headers,
                                 Map<String, ?> queryParams,
                                 ParameterizedTypeReference<T> responseType) {

        return buildRequestSpec(url, method, body, headers, queryParams)
                .body(responseType);
    }

    private RestClient.ResponseSpec buildRequestSpec(String url,
                                                     HttpMethod method,
                                                     Object body,
                                                     HttpHeaders headers,
                                                     Map<String, ?> queryParams) {

        RestClient.RequestBodySpec requestSpec = restClient.method(method)
                .uri(buildUrlWithParams(url, queryParams))
                .accept(APPLICATION_JSON)
                .headers(httpHeaders -> {
                    if (headers != null) {
                        httpHeaders.putAll(headers);
                    }
                });

        if (body != null) {
            requestSpec = requestSpec.body(body);
        }

        return requestSpec
                .retrieve()
                .onStatus(HttpStatusCode::isError, this::handleErrorResponse);
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

    private void handleErrorResponse(HttpRequest request, ClientHttpResponse response) throws IOException {
        // 응답 본문을 문자열로 읽기
        String responseBody = new String(response.getBody().readAllBytes());

        log.error(">>> Request URL: {}, Method: {}", request.getURI(), request.getMethod());
        log.error(">>> Response Status: {}, Headers: {}", response.getStatusCode(), response.getHeaders());
        log.error(">>> Response Body: {}", responseBody);

        // 예외 메시지 생성
        String exceptionMessage = String.format(
                "Request failed: [Method: %s, URL: %s, Status: %s, Response Body: %s]",
                request.getMethod(),
                request.getURI(),
                response.getStatusCode(),
                responseBody
        );

        throw new RuntimeException(exceptionMessage);
    }
}
