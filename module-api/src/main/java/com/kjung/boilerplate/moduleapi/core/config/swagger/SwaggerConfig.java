package com.kjung.boilerplate.moduleapi.core.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Swagger 설정
 * <a href="https://springdoc.org/#getting-started"/> 참고
 */
@Configuration
public class SwaggerConfig {

    @Bean
    @ConditionalOnBean(SwaggerInfoProperties.class)
    public OpenAPI customOpenAPI(SwaggerInfoProperties properties) {
        OpenAPI openAPI = createCustomOpenAPI(properties);

        // JWT 헤더 추가 여부 확인
        if (Boolean.TRUE.equals(properties.getJwtHeaderEnabled())) {
            openAPI.components(
                    new Components()
                            .addSecuritySchemes(
                                    HttpHeaders.AUTHORIZATION,
                                    new SecurityScheme()
                                            .type(SecurityScheme.Type.HTTP)
                                            .scheme("bearer")
                                            .bearerFormat("JWT")
                                            .name(HttpHeaders.AUTHORIZATION))
            );
        }

        return openAPI;
    }

    private OpenAPI createCustomOpenAPI(SwaggerInfoProperties properties) {
        Info info = new Info()
                .title(properties.getTitle())
                .description(properties.getDescription())
                .version(properties.getVersion());

        // Contact 정보 추가
        SwaggerInfoProperties.Contact contact = properties.getContact();

        if (contact != null) {
            info.setContact(
                    new Contact()
                            .name(contact.getName())
                            .email(contact.getEmail())
                            .url(contact.getUrl())
            );
        }

        // License 정보 추가
        SwaggerInfoProperties.License license = properties.getLicense();

        if (license != null) {
            info.setLicense(
                    new License()
                            .name(license.getName())
                            .url(license.getUrl())
            );
        }

        return new OpenAPI().info(info);
    }

    @Bean
    public OpenApiCustomizer customGlobalResponses() { // 공통 APIResponse 정의
        return openApi -> openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {

//                    addApiResponse(operation, BAD_REQUEST); // 400
//                    addApiResponse(operation, UNAUTHORIZED); // 401
//                    addApiResponse(operation, FORBIDDEN); // 403
            addApiResponse(operation, INTERNAL_SERVER_ERROR); // 500

        }));
    }

    private void addApiResponse(Operation operation, HttpStatus httpStatus) {
        operation
                .getResponses()
                .addApiResponse(
                        String.valueOf(httpStatus.value()),
                        new ApiResponse()
                                .description(httpStatus.getReasonPhrase())
                                .content(
                                        new Content()
                                                .addMediaType(
                                                        org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
                                                        new MediaType()
                                                                .example(Map.of("error", httpStatus.getReasonPhrase())) // todo 에러 DTO 필요
                                                )
                                )
                );
    }
}
