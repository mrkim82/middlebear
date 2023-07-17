package com.groo.bear.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Bean	//== @Component
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	CustomFailureHandler authenticationFailureHandler() {
		return new CustomFailureHandler();
	}
	
	@Bean
	CustomSuccessHandler authenticationSuccessHandler() {
		return new CustomSuccessHandler();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeHttpRequests()
			//.antMatchers("/status", "images/**", "/js/", "auth/join").permitAll()
			//.antMatchers("/forgotId", "/forgotPw", "/main").permitAll()
			//.antMatchers("/emp/**").hasRole("ADMIN")
			.anyRequest().permitAll()//authenticated()
			.and()
			.formLogin()
			.successHandler(authenticationSuccessHandler())
			.failureHandler(authenticationFailureHandler())
			.loginPage("/login")
			.permitAll()
			.and()
			.logout((logout) -> logout.permitAll());

		return http.build();
	}
}