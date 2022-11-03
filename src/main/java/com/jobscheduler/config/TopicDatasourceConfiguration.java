package com.jobscheduler.config;

import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.jobscheduler.topic.entity", entityManagerFactoryRef = "topicsEntityManagerFactory", transactionManagerRef = "topicsTransactionManager")
public class TopicDatasourceConfiguration {

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.topic")
	public DataSource productDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean topicsEntityManagerFactory(
			@Qualifier("topicsDataSource") DataSource dataSource, EntityManagerFactoryBuilder builder) {
		return builder.dataSource(dataSource).packages(new String[] { "com.jobscheduler.topic.entity" }).build();
	}

	@Bean
	public PlatformTransactionManager topicsTransactionManager(
			@Qualifier("topicsEntityManagerFactory") LocalContainerEntityManagerFactoryBean topicsEntityManagerFactory) {
		return new JpaTransactionManager(Objects.requireNonNull(topicsEntityManagerFactory.getObject()));
	}
}
