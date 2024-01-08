package com.abhijeet.noteapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class})
public class NoteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoteServiceApplication.class, args);
	}

}
