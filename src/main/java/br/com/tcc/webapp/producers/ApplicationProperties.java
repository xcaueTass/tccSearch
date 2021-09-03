package br.com.tcc.webapp.producers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class ApplicationProperties {

	@Value("${timeout.connect}")
	private int timeoutConnect;

	@Value("${timeout.read}")
	private int timeoutRead;

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.hikari.pool-name}")
	private String poolName;

	@Value("${spring.datasource.hikari.minimum-idle}")
	private int minumunIdle;

	@Value("${spring.datasource.hikari.maximum-pool-size}")
	private int maximunPoolSize;

	@Value("${spring.datasource.hikari.connection-timeout}")
	private Long connectionTimeout;

	@Value("${spring.datasource.hikari.idle-timeout}")
	private Long idleTimeout;

	@Value("${spring.datasource.hikari.max-lifetime}")
	private Long maxLifetime;
	
}