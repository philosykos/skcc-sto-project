package com.skcc.sto.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        final OpenAPI api = new OpenAPI()
            .addServersItem(new Server().url("http://localhost:8080"))
            .info(new Info()
                .title("SKCC STO Project API")
                .version("1.0.0")
                .description("SKCC STO Project API"))
            .components(new Components());
        return api;
    }
}