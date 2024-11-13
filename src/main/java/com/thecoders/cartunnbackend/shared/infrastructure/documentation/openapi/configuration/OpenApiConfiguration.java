package com.thecoders.cartunnbackend.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;
// http://localhost:8080/swagger-ui/index.html

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI cartunnOpenApi() {
        var openApi = new OpenAPI();
        openApi
                .info(new Info()
                        .title("CARTUNN Backend API - Applications for Mobile Devices")
                        .description("CARTUNN Backend application REST API documentation.")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .externalDocs(new ExternalDocumentation()
                        .description("CARTUNN company")
                        .url("https://legendary-banoffee-4f9ca1.netlify.app/"));

        //Servers
        openApi.servers(List.of(
                new Server().url("http://localhost:8080").description("Development Server"),
                new Server().url("https://xddd.up.railway.app").description("Production Server")
        ));

        // Add Security Scheme
        final String securitySchemeName = "bearerAuth";
        openApi.addSecurityItem(
                        new SecurityRequirement()
                                .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
        // Return OpenAPI configuration object*/
        return openApi;
    }
}
