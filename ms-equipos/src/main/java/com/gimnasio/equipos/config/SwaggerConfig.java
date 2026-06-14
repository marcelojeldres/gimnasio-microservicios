package com.gimnasio.equipos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API ms-equipos - Gimnasio")
                        .version("1.0.0")
                        .description("Gestión de equipos del gimnasio. ms-resenas consulta este servicio para validar equipos.")
                        .contact(new Contact().name("Gimnasio").email("soporte@gimnasio.cl"))
                        .license(new License().name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}
