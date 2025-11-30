package com.gab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.gab.TestData.DataService;

@SpringBootApplication
@ComponentScan(basePackages = "com.gab")
public class LibraryApiApplication {

	public static void main(String[] args) {
		DataService.initTestData();
		SpringApplication.run(LibraryApiApplication.class, args);
	}

}
