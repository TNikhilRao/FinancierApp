package com.financier.FinancierApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Nikhil.Tirmanwar
 *
 */
@SpringBootApplication(scanBasePackages = { "com.financier" })
@EnableAspectJAutoProxy
@EnableMongoRepositories({ "com.financier" })
//@Import({ SwaggerConfiguration.class })
public class FinancierApp extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(FinancierApp.class, args);
	}
}
