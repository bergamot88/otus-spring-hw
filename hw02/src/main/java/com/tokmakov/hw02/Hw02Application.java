package com.tokmakov.hw02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.tokmakov.hw02.services.QuizService;

@ComponentScan
public class Hw02Application {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Hw02Application.class);
		QuizService quizService = context.getBean(QuizService.class);
		quizService.runQuiz();
	}

}
