package br.com.tcc.webapp.configs;

import java.time.Duration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import br.com.tcc.webapp.producers.ApplicationProperties;

@Configuration
public class DataSourceConfig {

	@Autowired
	ApplicationProperties appProp;

	@Bean
	public DataSource getDataSource() {
		HikariConfig config = new HikariConfig();
		config.setMinimumIdle(appProp.getMinumunIdle());
		config.setMaximumPoolSize(appProp.getMaximunPoolSize());
		config.setPoolName(appProp.getPoolName());
		config.setConnectionTimeout(appProp.getConnectionTimeout());
		config.setIdleTimeout(appProp.getIdleTimeout());
		config.setMaxLifetime(appProp.getMaxLifetime());
		config.setJdbcUrl(appProp.getUrl());
		config.setDriverClassName(appProp.getDriverClassName());
		config.setUsername(appProp.getUsername());
		config.setPassword(appProp.getPassword());

		return new HikariDataSource(config);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {

		return restTemplateBuilder.setConnectTimeout(Duration.ofMillis(appProp.getTimeoutConnect()))
				.setReadTimeout(Duration.ofMillis(appProp.getTimeoutRead())).build();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate(getClientHttpRequestFactory());
	}

	private HttpComponentsClientHttpRequestFactory getClientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		// Connect timeout
		clientHttpRequestFactory.setConnectTimeout(appProp.getTimeoutConnect());

		// Read timeout
		clientHttpRequestFactory.setReadTimeout(appProp.getTimeoutRead());
		return clientHttpRequestFactory;
	}

}