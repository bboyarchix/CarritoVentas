package com.example.microserviceinventory;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@EnableFeignClients
@EnableJpaAuditing
@SpringBootApplication
public class MicroserviceInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceInventoryApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info()
				.title("OPEN API MICROSERVICIO INVENTARIO")
				.version("0.0.1")
				.description("servicios web inventario")
				.termsOfService("http:// swagger.io/terms")
				.license(new License().name("Apache 2.0").url("http://springdoc.org"))
		);
	}

}
