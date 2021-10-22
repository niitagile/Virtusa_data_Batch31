package com.springBoot.ConnectGlobe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class GlobeConfig  extends WebSecurityConfigurerAdapter{

	@Autowired
	UserDetailsService userService;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors().and()
		.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/register").permitAll()
			.antMatchers("/save").permitAll()
			.antMatchers("/login").permitAll()
			.antMatchers("/MyPosts/upload").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/MyPosts").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/home").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/AllReports").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/MyReports").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/uploadReport").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/comment").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/AllComments").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/back").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/suggest").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/getAllSuggest").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/return").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/delete").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/deletePost").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/Profile").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/Edit").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/getUserProfile").hasAuthority("ADMIN")
			.antMatchers("/deleteUserProfile").hasAuthority("ADMIN")
			.antMatchers("/adminHome").hasAuthority("ADMIN")
			.anyRequest().authenticated()
			.and()
			.logout().invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/").permitAll();
	http.csrf().disable();
	}
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}

}
