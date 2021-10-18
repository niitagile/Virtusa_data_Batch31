package com.cityclassifiedandsearch.citysearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories("com.cityclassifiedandsearch.repo")
@EntityScan("com.cityclassifiedandsearch.bean")
@ComponentScan(basePackages = {"com.cityclassifiedandsearch.config","com.cityclassifiedandsearch.controller", "com.cityclassifiedandsearch.service"})
public class CityClassifiedAndSearchApplication {
	public static void main(String[] args) {
		SpringApplication.run(CityClassifiedAndSearchApplication.class, args);
	}
}
