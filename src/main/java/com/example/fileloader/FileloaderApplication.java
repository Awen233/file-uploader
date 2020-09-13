package com.example.fileloader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class FileloaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileloaderApplication.class, args);
	}

}
