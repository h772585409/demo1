package com.molin.project200908.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/imgs/**").addResourceLocations("file:C:/Users/86134/IdeaProjects/project200907/src/main/resources/static/imgs/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080")
                .allowedHeaders("Accept","Content-Type","Origin","Authorization","X-Auth-Token")
                .exposedHeaders("X-Auth-Token","Authorization")
                .allowedMethods("POST","GET","DELETE","PUT","OPTIONS","TRACE","PATCH")
                .allowCredentials(true);

    }
}
