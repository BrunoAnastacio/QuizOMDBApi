package org.quizapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class QuizapiApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(QuizapiApplication.class, args);

	}

}
