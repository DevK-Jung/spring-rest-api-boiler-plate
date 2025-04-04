package com.kjung.boilerplate.httpclient.httpInterface.config;

import com.kjung.boilerplate.httpclient.httpInterface.SampleHttpInterfaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Slf4j
@Configuration
public class HttpInterfaceConfig {
    @Bean
    public SampleHttpInterfaceService sampleHttpInterfaceService(@Value("${http-client.sample.base-url}") String baseUrl) {
        RestClient restClient = createRestClient(baseUrl);
        HttpServiceProxyFactory factory = createHttpServiceProxyFactory(restClient);
        return factory.createClient(SampleHttpInterfaceService.class);
    }

    private RestClient createRestClient(String baseUrl) {
        return RestClient.builder()
                .baseUrl(baseUrl)
                .requestFactory(clientHttpRequestFactory())
                .defaultStatusHandler(HttpStatusCode::isError, this::handleErrorResponse)
                .build();
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        return new SimpleClientHttpRequestFactory();
    }

    private void handleErrorResponse(org.springframework.http.HttpRequest request, org.springframework.http.client.ClientHttpResponse response) {
        try {
            log.error(">> RestClient error response Status: {}", response.getStatusCode());
            log.error(">> RestClient error response URI: {}", request.getURI());
        } catch (Exception e) {
            log.error(">> Error logging response details", e);
        }
        throw new RuntimeException("RestClient error");
    }

    private HttpServiceProxyFactory createHttpServiceProxyFactory(RestClient restClient) {
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        return HttpServiceProxyFactory.builderFor(adapter).build();
    }
}
