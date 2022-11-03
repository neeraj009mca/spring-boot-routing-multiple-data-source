package com.jobscheduler.config;

import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.jobscheduler.todo.entity", entityManagerFactoryRef = "todosEntityManagerFactory", transactionManagerRef = "todosTransactionManager")
public class TodoDatasourceConfiguration {

	@Bean
	@ConfigurationProperties("spring.datasource.todos")
	public DataSourceProperties todosDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@Primary
	public DataSource todosDataSource() {
		return todosDataSourceProperties().initializeDataSourceBuilder().build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean todosEntityManagerFactory(
			@Qualifier("todosDataSource") DataSource dataSource, EntityManagerFactoryBuilder builder) {
		return builder.dataSource(dataSource).packages(new String[] { "com.jobscheduler.todo.entity"}).build();
	}

	@Bean
	public PlatformTransactionManager todosTransactionManager(
			@Qualifier("todosEntityManagerFactory") LocalContainerEntityManagerFactoryBean todosEntityManagerFactory) {
		return new JpaTransactionManager(Objects.requireNonNull(todosEntityManagerFactory.getObject()));
	}
}
