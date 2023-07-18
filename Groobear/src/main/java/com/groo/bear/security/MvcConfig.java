package com.groo.bear.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("../static/index");
		registry.addViewController("/main").setViewName("main/main");
		registry.addViewController("/login").setViewName("../static/index");
		registry.addViewController("/forgotId").setViewName("main/forgetId");
		registry.addViewController("/forgotPw").setViewName("main/forgetPw");
		registry.addViewController("/changePw").setViewName("main/changePw");
	}

}