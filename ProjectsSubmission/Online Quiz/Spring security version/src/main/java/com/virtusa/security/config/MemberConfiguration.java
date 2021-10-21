package com.virtusa.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class MemberConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/resources/**", "/registration","/","/expertlogin","/adminlogin","/subjects/{std_id}","/student/profile/{studentId}",
                    		"/student/register","/student/login","/check/student/login","/getQuestions/{sub_id}","/getResult","/expertdashboard",
                    		"/add/subject","/addsubject","/add/question","/addquestions","/check/expert/login","/admindashboard","/check/admin/login",
                    		"/addexpert","/addstudent","/add/expert","/delete/expert","/add/student","/delete/student","/view/students","/view/experts",
                    		"/view/results","/add/admin").permitAll()
                    .anyRequest().authenticated()
                    .and()

                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/subjects/7")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
