package com.hernandes.assembleia.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket assembleia() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.hernandes.assembleia.resources"))
				.build()
				.apiInfo(metadata());
	}

	private ApiInfo metadata() {
		return new ApiInfoBuilder()
				.title("Assembléia")
				.description("documentação de assembléia")
				.version("1.0")
				.build();
	}
}
