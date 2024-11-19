package com.springboottrain.app.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/writeForm").setViewName("views/wirteForm");
		registry.addViewController("/writeBoard").setViewName("views/WriteForm");
		
		registry.addViewController("/joinForm").setViewName("member/memberJoinForm");
		//로그인 폼 뷰 전용 컨트롤러 설정 추가
		registry.addViewController("/loginForm").setViewName("member/loginForm");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/resources/files/**")
		.addResourceLocations("file:./src/main/resources/static/files/")
		.setCachePeriod(1);
	}
	

}
