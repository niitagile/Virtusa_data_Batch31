package com.pharmacy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@EnableJdbcHttpSession
@SpringBootApplication
public class OnlinePharmacySbApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(OnlinePharmacySbApplication.class, args);
	}

}
