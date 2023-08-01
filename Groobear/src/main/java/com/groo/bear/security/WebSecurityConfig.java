package com.groo.bear.security;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


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
			.antMatchers("/forgotId", "/forgotPw", "/signUp", "/signUpInfo").permitAll()
			.anyRequest().authenticated()
			.and()
			.headers().frameOptions().sameOrigin()
			.and()
			.formLogin()
			.successHandler(authenticationSuccessHandler())
			.failureHandler(authenticationFailureHandler())
			.loginPage("/login")
			.permitAll();
		
			http.logout()
	        .logoutUrl("/logout")   // 로그아웃 처리 URL (= form action url)
	        .addLogoutHandler((request, response, authentication) -> { 
	            // 사실 굳이 내가 세션 무효화하지 않아도 됨. 
	            // LogoutFilter가 내부적으로 해줌.
	            HttpSession session = request.getSession();
	            if (session != null) {
	                session.invalidate();
	            }
	        })  // 로그아웃 핸들러 추가
	        .logoutSuccessHandler((request, response, authentication) -> {
	            response.sendRedirect("/login");
	        }) // 로그아웃 성공 핸들러
	        .deleteCookies("remember-me"); // 로그아웃 후 삭제할 쿠키 지정

		return http.build();
	}
}