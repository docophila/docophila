package org.sber.www.docophila.ru;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("4Mb");
		factory.setMaxRequestSize("4Mb");
		return factory.createMultipartConfig();
	}


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}
}