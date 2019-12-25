package com.fronteo.cms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Autowired
	private HttpInterceptor interceptor;
	
	@Override 
	public void addInterceptors(InterceptorRegistry registry) { 
		registry.addInterceptor(interceptor) 
				.addPathPatterns("/board/**")
				.addPathPatterns("/contents/**")
				.addPathPatterns("/page/**")
				.addPathPatterns("/salesforce/**");
//				.excludePathPatterns("/login"); 
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}
}