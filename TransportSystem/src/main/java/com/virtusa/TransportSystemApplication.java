package com.virtusa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication
@CrossOrigin(origins= "*")
public class TransportSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransportSystemApplication.class, args);
	}

}
