package net.octacomm.sample.config;

import javax.sql.DataSource;

import net.octacomm.sample.domain.Domain;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:bootstrap.properties")
@EnableTransactionManagement
@MapperScan(basePackages = { "net.octacomm.sample.dao.mapper" }, sqlSessionFactoryRef = "sqlSessionFactory")
public class MyBatisConfig {
	
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(destroyMethod = "close")
	public DataSource dataSource(
			@Value("#{environment['jdbc.driverClass']}") String driverClassName,
			@Value("#{environment['jdbc.url']}") String url,
			@Value("#{environment['jdbc.username']}") String username,
			@Value("#{environment['jdbc.password']}") String password) {
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setValidationQuery("select 1");
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(
					DataSource dataSource, ResourceLoader loader, ResourcePatternResolver resolver) throws Exception {
		
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setConfigLocation(loader.getResource("classpath:mybatis-config.xml"));
		factoryBean.setTypeAliasesPackage(Domain.class.getPackage().toString());
		factoryBean.setMapperLocations(resolver.getResources("classpath:net/octacomm/sample/dao/mapper/**/*.xml"));
		
		return factoryBean.getObject();
	}
	
}
