package net.octacomm.sample.config;

import net.octacomm.logger.LoggerBeanPostProcessor;
import net.octacomm.network.NioTcpServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan(basePackages = "net.octacomm.sample.service")
@EnableCaching
@Import(MyBatisConfig.class)
public class AppConfig {

	@Bean
	public BeanPostProcessor loggerBeanPostProcessor() {
		return new LoggerBeanPostProcessor();
	}
	
	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager();
	}
	
}
