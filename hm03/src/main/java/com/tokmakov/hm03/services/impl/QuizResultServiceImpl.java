package com.tokmakov.hm03.services.impl;

import com.tokmakov.hm03.config.ConfigProperties;
import com.tokmakov.hm03.models.Question;
import com.tokmakov.hm03.services.IOService;
import com.tokmakov.hm03.services.QuizResultService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class QuizResultServiceImpl implements QuizResultService {

    private final IOService ioService;
    private final ConfigProperties quizProperties;

    public QuizResultServiceImpl(ConfigProperties quizProperties, 
                                IOService ioService) {
        this.quizProperties = quizProperties;
        this.ioService = ioService;
    }

    @Override
    public void showResult(int userCorrectAnswersNumber) {
        ioService.outln(String.format("You result: %s ", userCorrectAnswersNumber));
        int correctAnswersNumber = quizProperties.getCorrectAnswersNumber();
        if(userCorrectAnswersNumber < correctAnswersNumber) {
            ioService.outln("YOU FAIL");
        } else {
            ioService.outln("YOU PASS");
        }
    }

    @Override
    public boolean isCorrectUserAnswer(Question question, int userAnswer) {
        boolean isCorrectInput = true;
        boolean isCorrectAnswer = false;
        do {
            try {
                isCorrectAnswer = question.isCorrectAnswerById(userAnswer);
                isCorrectInput = true;
            } catch (NoSuchElementException e) {
                isCorrectInput = false;
                ioService.outln("You entered a number that does not exist");
            }
        } while(!isCorrectInput);
        return isCorrectAnswer;
    }
}
