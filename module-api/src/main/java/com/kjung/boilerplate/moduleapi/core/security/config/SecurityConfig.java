package com.kjung.boilerplate.moduleapi.core.security.config;

import com.kjung.boilerplate.moduleapi.core.security.AdminOnlyAuthorizationManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${cors.allowed-origin}")
    private List<String> allowedOrigin;

    @Value("${security.public-uris}")
    private String[] publicUris;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   AdminOnlyAuthorizationManager adminOnlyAuthorizationManager) throws Exception {

        http.cors(c -> c.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(r -> r.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //세션 사용 안함

                .authorizeHttpRequests(r ->
                        r.requestMatchers(POST, "/api/*/system/**").access(adminOnlyAuthorizationManager)
                                .requestMatchers(PUT, "/api/*/system/**").access(adminOnlyAuthorizationManager)
                                .requestMatchers(DELETE, "/api/*/system/**").access(adminOnlyAuthorizationManager)
                                .anyRequest().permitAll()
                );

//                .authorizeHttpRequests(r ->
//                        r.requestMatchers(publicUris).permitAll()
//                                .anyRequest().authenticated()
//                );


        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(allowedOrigin);

        configuration.setAllowedMethods(Arrays.asList(
                GET.name(),
                POST.name(),
                PUT.name(),
                DELETE.name(),
                HEAD.name(),
                OPTIONS.name()
        ));

        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);

        return source;
    }

//    @Bean
//    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
//
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//
//        return new ProviderManager(authenticationProvider);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
