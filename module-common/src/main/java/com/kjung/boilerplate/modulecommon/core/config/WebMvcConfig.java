package com.kjung.boilerplate.modulecommon.core.config;

import com.kjung.boilerplate.modulecommon.core.interceptor.RequestContextInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestContextInterceptor())
                .addPathPatterns("/api/**"); // Interceptor를 적용할 URL 패턴
    }
}