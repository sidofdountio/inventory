package com.sidof.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.http.HttpHeaders.*;

@Configuration
@EnableWebMvc
public class ApplicationConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:4200","http://127.0.0.7:80")
                .allowedMethods(
                        "POST","GET","DELETE","PUT"
                )
                .allowedHeaders(
                        ACCEPT,
                        AUTHORIZATION,
                        CONTENT_TYPE,
                        ORIGIN,"Jwt-Token",
                        ACCESS_CONTROL_ALLOW_ORIGIN,
                        ACCESS_CONTROL_REQUEST_METHOD,
                        ACCESS_CONTROL_ALLOW_HEADERS,
                        ACCESS_CONTROL_REQUEST_HEADERS,
                        "X-Requested-With","Origin, Accept"
                )
                .exposedHeaders(
                        ACCEPT,
                        AUTHORIZATION,
                        CONTENT_TYPE,
                        ORIGIN, ACCESS_CONTROL_ALLOW_ORIGIN,
                        ACCESS_CONTROL_ALLOW_CREDENTIALS,
                        "Jwt-Token","Filename"
                )
                .allowCredentials(true).maxAge(3600);
    }
}
