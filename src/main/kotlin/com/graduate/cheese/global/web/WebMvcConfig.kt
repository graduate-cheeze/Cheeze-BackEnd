package com.graduate.cheese.global.web

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins(
                "http://localhost:8080",
                "http://localhost:5173",
                "https://cheeze-front-end.vercel.app"
            )
            .allowedHeaders("*")
            .allowedMethods("*")
            .allowCredentials(true)
    }
}