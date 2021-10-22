package com.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	public SecurityConfig() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
//		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/", "/login", "/register", "/check", "/error").permitAll()

		.antMatchers("/admin", "/showall", "/viewdetail", "/close", 
				"/adminprofile", "/search", "/getallquery",
				"/status", "/adminloan").hasAuthority("ADMIN")
		
		.antMatchers("/user", "/dashboard", "/loan",
				"/fundtransfer", "/upitransfer", "/enableupi", "/getBalance",
				"/profile", "/transfer", "/passbook", "/upi", 
				"/raisequery", "/loanrequest").hasAuthority("USER")
		
		.antMatchers("/editprofile").hasAnyAuthority("USER", "ADMIN")
		
		 .and()
         .formLogin()
             .loginPage("/")
             .loginProcessingUrl("/login")
             .defaultSuccessUrl("/loginsetsession", true)
             .passwordParameter("password")
             .usernameParameter("username")
         .and()
         .logout()
             .logoutUrl("/logout")
             .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) 
             .clearAuthentication(true)
             .invalidateHttpSession(true)
             .logoutSuccessUrl("/logoutsession");
			
	}
	
//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
	
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder(15);
	}

}
