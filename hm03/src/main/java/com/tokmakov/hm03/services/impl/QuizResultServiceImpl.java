package com.tokmakov.hm03.services.impl;

import com.tokmakov.hm03.config.ConfigProperties;
import com.tokmakov.hm03.models.Question;
import com.tokmakov.hm03.services.IOService;
import com.tokmakov.hm03.services.LocalizationService;
import com.tokmakov.hm03.services.QuizResultService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class QuizResultServiceImpl implements QuizResultService {

    private final IOService ioService;
    private final ConfigProperties quizProperties;
    private final LocalizationService localizationService;

    public QuizResultServiceImpl(ConfigProperties quizProperties, 
                                IOService ioService,
                                LocalizationService localizationService) {
        this.quizProperties = quizProperties;
        this.ioService = ioService;
        this.localizationService = localizationService;
    }

    @Override
    public void showResult(int userCorrectAnswersNumber) {
        ioService.outln(
            localizationService.getLocalizationMessage("you.result", userCorrectAnswersNumber)
        );
        int correctAnswersNumber = quizProperties.getCorrectAnswersNumber();
        if(userCorrectAnswersNumber < correctAnswersNumber) {
            ioService.outln(localizationService.getLocalizationMessage("you.fail"));
        } else {
            ioService.outln(localizationService.getLocalizationMessage("you.pass"));
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
                ioService.outln(localizationService.getLocalizationMessage("exception.number.not.exit"));
            }
        } while(!isCorrectInput);
        return isCorrectAnswer;
    }
}
