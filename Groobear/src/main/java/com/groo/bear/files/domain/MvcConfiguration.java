package com.groo.bear.files.domain;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

   @Override
   public void addResourceHandlers(final ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/upload/**")
               .addResourceLocations("file:///c:/upload/")
               .setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));
   }
}
