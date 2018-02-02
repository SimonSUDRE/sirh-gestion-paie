package dev.paie.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class DataSourceMySQLConfig {
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://bgijo5aoi-mysql.services.clever-cloud.com:3306/bgijo5aoi?useSSL=false");
		dataSource.setUsername("uvu9v7ese4bc8qq4");
		dataSource.setPassword("MOOkegO3ndt8sJ3iirY");
		return dataSource;
	}

	public DataSource dataSourceH2() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript("bdd.sql")
				.build();
	}
}