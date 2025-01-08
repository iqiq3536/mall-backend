package com.example.mallxx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
//
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 配置所有以 /api/ 开头的路径允许来自 http://localhost:8082 的跨域请求
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:8082")// 允许的来源地址
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowedHeaders("Content-Type", "Authorization", "*")
                .allowCredentials(true)
                .maxAge(3600);//3600秒
    }
}
