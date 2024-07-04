package com.fiap.techchallenger5.msauthusers.infra.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
 @Value("${spring.profiles.active:Unknown}")
    String activeProfile;

    @Value("${spring.application.name:Unknown}")
    String applicationName;

    @Value("${server.port:Unknown}")
    String serverPort;

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

        if("docker".equals(activeProfile)){
            securityItem.servers(Arrays.asList(getServer()));
        }
        return securityItem;
    }

     private Server getServer() {
        Server server = new Server();
        server.setDescription("Gateway Docker");
        server.setUrl("http://"+applicationName+":"+serverPort);
        return server;
    }
}
