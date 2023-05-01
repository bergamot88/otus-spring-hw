package com.tokmakov.hm03;

import com.tokmakov.hm03.services.QuizService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Hm03Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Hm03Application.class, args);
		QuizService quizService = context.getBean(QuizService.class);
		quizService.runQuiz();
	}
}
