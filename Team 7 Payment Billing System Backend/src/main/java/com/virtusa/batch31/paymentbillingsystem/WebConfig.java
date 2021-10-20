package com.virtusa.batch31.paymentbillingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	private RequestInterceptor requestInterceptor;
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
        .addMapping("/**")
        .allowedMethods("PUT","DELETE","GET","POST","OPTIONS");  
    }

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestInterceptor)
		.excludePathPatterns("/login/**", "/admin/**");
	}
	
	
}
