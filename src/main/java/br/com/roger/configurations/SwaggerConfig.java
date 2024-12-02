package br.com.roger.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI customOpenApi() {

		OpenAPI openAPI = new OpenAPI()
				.components(new Components().addSecuritySchemes("bearerAuth",
						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
				.info(new Info().title("API Funcionários - Sistemas de gerenciamento de Funcionários.")
						.description("Roger Ribeiro Santos").version("v1")
						.contact(new Contact().email("roger17ribeiro09@gmail.com")
								.url("https://www.linkedin.com/in/rogerrsantoss/")));

		return openAPI;

	}
}
