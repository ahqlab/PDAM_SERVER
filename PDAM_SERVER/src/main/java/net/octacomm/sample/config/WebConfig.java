package net.octacomm.sample.config;

import net.octacomm.logger.LoggerBeanPostProcessor;
import net.octacomm.sample.interceptor.LogInterceptor;
import net.octacomm.sample.interceptor.LoginInterceptor;
import net.octacomm.sample.view.ConnectStatsExcelView;
import net.octacomm.sample.view.FileUsingChartExcelView;
import net.octacomm.sample.view.TestSlsxExcelView;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import org.springframework.web.servlet.view.tiles2.TilesConfigurer;
import org.springframework.web.servlet.view.tiles2.TilesViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "net.octacomm.sample.controller")
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public BeanPostProcessor loggerBeanPostProcessor() {
		return new LoggerBeanPostProcessor();
	}
		
	@Bean
	public AbstractExcelView connectStatsExcelView() {
		return new ConnectStatsExcelView();
	}
	
	@Bean
	public AbstractExcelView fileUsingChartExcelView() {
		return new FileUsingChartExcelView();
	}
	
	@Bean
	public HandlerInterceptor logInterceptor() {
		return new LogInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		String patterns[] = new String[] { 
				"/**"
		}; 
		registry.addInterceptor(logInterceptor())
						.addPathPatterns("/**");
		registry.addInterceptor(new LoginInterceptor())
						.addPathPatterns(patterns)
						.excludePathPatterns("/login")
						.excludePathPatterns("/mobile/device/login")
						.excludePathPatterns("/mobile/device/all/list")
						.excludePathPatterns("/api/get/report/list")
						.excludePathPatterns("/api/get/auth/check")
						.excludePathPatterns("/mobile/regist/report");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("/resources/");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		return multipartResolver;
	}
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(
				new String[]{"classpath:tilesdef-admin.xml"});
		return configurer;
	}
	
	@Bean
	public TilesViewResolver tilesViewResolver() {
		TilesViewResolver resolver = new TilesViewResolver();
		resolver.setOrder(1);
		return resolver;
	}
	
	@Bean
	public BeanNameViewResolver beanNameViewResolver() {
		BeanNameViewResolver resolver = new BeanNameViewResolver();
		resolver.setOrder(0);
		return resolver;
	}
}
