package com.Cart.CartService.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI OpenApiConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cart Service")
                        .version("1.0")
                        .description("Contains all products")
                        .contact(new Contact()
                                .name("")
                                .email("")));
    }
}