package com.example.fileloader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableConfigurationProperties
public class FileloaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileloaderApplication.class, args);
	}



	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
