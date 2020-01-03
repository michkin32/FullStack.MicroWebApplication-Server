package com.groupfour.chatapp.chatapp;

import com.groupfour.chatapp.chatapp.property.FileStorageProperties;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class ChatappApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatappApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
