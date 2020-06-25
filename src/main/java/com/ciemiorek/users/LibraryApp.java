package com.ciemiorek.users;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource(value = "classpath:message.properties", encoding = "UTF-8")
public class LibraryApp implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(LibraryApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {




	}
}
