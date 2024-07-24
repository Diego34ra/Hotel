package br.edu.ifgoiano.hotel.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OpenApiConfig {

    @Bean
    public OpenAPI myOpenAPI() {

        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.setDescription("URL do servidor no ambiente de desenvolvimento");

//        Server prodServer = new Server();
//        prodServer.setUrl("https://projeto-ecommerce-production.up.railway.app");
//        prodServer.setDescription("URL do servidor no ambiente de produção");

        Contact contact = new Contact();
        contact.setEmail("diegoribeiro13ra@hotmail.com");
        contact.setName("Diego Ribeiro Araújo");
        contact.setUrl("mailto:diegoribeiro13ra@hotmail.com");

//        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Api Projeto Ecommerce")
                .version("1.0")
                .contact(contact)
                .description("Esta API apresenta os endpoints de um projeto de ecommerce.").termsOfService("");
//                .license(mitLicense);

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer,prodServer));
    }
}
