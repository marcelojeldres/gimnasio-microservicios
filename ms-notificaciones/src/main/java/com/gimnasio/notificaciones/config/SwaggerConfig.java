package com.gimnasio.notificaciones.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("API ms-notificaciones - Gimnasio")
                .version("1.0.0")
                .description("Envío de notificaciones por correo (modo simulación en local)"));
    }
}
