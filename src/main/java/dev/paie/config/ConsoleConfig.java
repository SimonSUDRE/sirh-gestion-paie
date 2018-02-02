package dev.paie.config;

import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("dev.paie.ihm")
@Import(ServicesConfig.class)
public class ConsoleConfig {

	@Bean
	@Scope("prototype")
	public Scanner scanner() {
		return new Scanner(System.in);
	}
}
