package com.tokmakov.hm03.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.tokmakov.hm03.config.ConfigProperties;
import com.tokmakov.hm03.dao.QuizDao;
import com.tokmakov.hm03.exceptions.SourceReadException;
import com.tokmakov.hm03.models.Question;
import com.tokmakov.hm03.models.User;
import com.tokmakov.hm03.models.UserQuizResult;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizDao quizDao;
    private final IOService ioService;
    private final ConfigProperties quizProperties;

    public QuizServiceImpl(ConfigProperties quizProperties,
                            QuizDao quizDao,
                            IOService ioService) {
        this.quizProperties = quizProperties;
        this.quizDao = quizDao;
        this.ioService = ioService;
    }

    @Override
    public void runQuiz() {
        UserQuizResult userQuizResult = new UserQuizResult();
        List<Question> questions = getQuestions();
        User user = askUserName();
        sayHelloToUser(user);
        askQuestionsToUserAndSaveResult(questions, userQuizResult);
        showResult(userQuizResult.getNumberRightQuestions());
    }

    private void sayHelloToUser(User user) {
        ioService.outln(String.format("\nHello %s %s !\n",
            user.getFirstName(), user.getLastName()));
        ioService.outln("Start testinng\n");
    }

    private User askUserName() {
        ioService.outln("\nPlease input you first name");
        String firstName = ioService.readString();
        ioService.outln("\nPlease input you last name");
        String lastName = ioService.readString();
        return new User(firstName, lastName);
    }

    private void askQuestionsToUserAndSaveResult(List<Question> questions, UserQuizResult userQuizResult) {
        int userCorrectAnswersNumber = 0;
        for (Question question : questions) {
            askQuestionToUser(question);
            if (isCorrectUserAnswer(question)) {
                userCorrectAnswersNumber++;
            }
        }
        userQuizResult.setNumberRightQuestions(userCorrectAnswersNumber);
    }

    private void askQuestionToUser(Question question) {
        ioService.outln(String.format("\t[%s] - %s", question.getId(), question.getQuestion()));
        question.getAnswers().forEach(answer ->
            ioService.outln(String.format("\t\t[%s] - %s", answer.getId(), answer.getContent()))
        );
    }

    private void showResult(int userCorrectAnswersNumber) {
        ioService.outln(String.format("You result: %s ", userCorrectAnswersNumber));
        int correctAnswersNumber = quizProperties.getCorrectAnswersNumber();
        if(userCorrectAnswersNumber < correctAnswersNumber) {
            ioService.outln("YOU FAIL");
        } else {
            ioService.outln("YOU PASS");
        }
    }

    private List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        try {
            questions = quizDao.findAllQuestions();
        } catch (SourceReadException ex) {
            ioService.outln(String.format("Could not read the questions from the source\n%s",
                ex.getMessage()));
        }
        return questions;
    }

    private boolean isCorrectUserAnswer(Question question) {
        boolean isCorrectInput = true;
        boolean isCorrectAnswer = false;
        int userAnswer = 0;
        do {
            try {
                userAnswer = getUserAnswer();
                isCorrectAnswer = question.isCorrectAnswerById(userAnswer);
                isCorrectInput = true;
            } catch (NoSuchElementException e) {
                isCorrectInput = false;
                ioService.outln("You entered a number that does not exist");
            }
        } while(!isCorrectInput);
        return isCorrectAnswer;
    }

    private int getUserAnswer() {
        boolean isCorrectInput = true;
        int userAnswer = 0;
        do {
            try {
                userAnswer = ioService.readInt();
                isCorrectInput = true;
            } catch (NumberFormatException e) {
                isCorrectInput = false;
                ioService.outln("You entered the wrong value. Only numbers are allowed");
            }
        } while(!isCorrectInput);
        return userAnswer;
    }
}
