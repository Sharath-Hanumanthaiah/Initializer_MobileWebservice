package com.initializers.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;

@SpringBootApplication
public class ApiServiceGraphQlApplication {

	@Bean
	public GraphQLScalarType dateType() {
		return ExtendedScalars.Date;
	} 
	public static void main(String[] args) {
		SpringApplication.run(ApiServiceGraphQlApplication.class, args);
	}

}
