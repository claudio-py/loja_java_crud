package com.loja.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
            .allowedOrigins(
               "http://localhost:5173",
               "http://localhost:3000"
               // "http://frontend:80" // From Docker container
            		// "*" // Allow all origins for testing purposes
            )
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedHeaders("*");
      }
    };
  }
}
