package com.tokmakov.hm03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.tokmakov.hm03.services.QuizService;

@SpringBootApplication
public class Hm03Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = 
			SpringApplication.run(Hm03Application.class, args);
		QuizService quizService = context.getBean(QuizService.class);
		quizService.runQuiz();
	}

}
