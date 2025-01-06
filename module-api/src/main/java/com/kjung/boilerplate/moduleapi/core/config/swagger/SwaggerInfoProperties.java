package com.kjung.boilerplate.moduleapi.core.config.swagger;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConditionalOnProperty(
        prefix = "springdoc.swagger-ui.info",
        name = "enabled",
        havingValue = "true"
)
@Configuration
@ConfigurationProperties(prefix = "springdoc.swagger-ui.info")
public class SwaggerInfoProperties {
    private boolean enabled;
    private String title;
    private String description;
    private String version;
    private Contact contact;
    private License license;
    private Boolean jwtHeaderEnabled;

    // Contact 클래스
    @Getter
    @Setter
    public static class Contact {
        private String name;
        private String email;
        private String url;

    }

    // License 클래스
    @Getter
    @Setter
    public static class License {
        private String name;
        private String url;

    }
}
