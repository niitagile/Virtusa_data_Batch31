package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@ComponentScan({"com.example.beans","com.example.repository","com.example.controller"})
public class OnlineQuizNaveenBranchApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineQuizNaveenBranchApplication.class, args);
	}

}
