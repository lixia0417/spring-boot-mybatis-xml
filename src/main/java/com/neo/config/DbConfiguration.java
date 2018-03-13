package com.neo.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DbConfiguration {

	/**
	 * Master data source.
	 */
	@Bean("masterDataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	DataSource masterDataSource() {
		System.err.println("create master datasource...");
		return DataSourceBuilder.create().build();
	}

	/**
	 * Slave (read only) data source.
	 */
	@Bean("slaveDataSource")
	@ConfigurationProperties(prefix = "spring.ro-datasource")
	DataSource slaveDataSource() {
		System.err.println("create slave datasource...");
		return DataSourceBuilder.create().build();
	}

}
