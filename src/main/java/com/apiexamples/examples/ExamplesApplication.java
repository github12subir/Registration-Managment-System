package com.apiexamples.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
//The default path for Swagger UI in Springfox is below----
//http://localhost:8080/swagger-ui/
@SpringBootApplication
@EnableSwagger2
public class ExamplesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamplesApplication.class, args);
	}

}
