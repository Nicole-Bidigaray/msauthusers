package com.fiap.techchallenger5.msauthusers.infra.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI customOpenAPI() {
        OpenAPI securityItem = new OpenAPI()
                .components(new Components().addSecuritySchemes("bearer-key",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("API do Microserviço de Autenticação e Autorização de Usuários")
                        .version("v1")
                        .description("API do Microserviço de Autenticação e Autorização de Usuários criada exclusivamente para o TechChallenge 5 da FIAP."))
                .addSecurityItem(new SecurityRequirement().addList("bearer-key"));
       
        return securityItem;
    }

}
