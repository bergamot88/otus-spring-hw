package com.tokmakov.hm03.services.impl;

import com.tokmakov.hm03.models.Question;
import com.tokmakov.hm03.models.User;
import com.tokmakov.hm03.models.UserQuizResult;
import com.tokmakov.hm03.services.IOService;
import com.tokmakov.hm03.services.LocalizationService;
import com.tokmakov.hm03.services.QuizResultService;
import com.tokmakov.hm03.services.UserDialogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDialogServiceImpl implements UserDialogService {

    private final IOService ioService;
    private final QuizResultService quizResultService;
    private final LocalizationService localizationService;

    public UserDialogServiceImpl(IOService ioService, 
                                 QuizResultService quizResultService,
                                 LocalizationService localizationService) {
        this.ioService = ioService;
        this.quizResultService = quizResultService;
        this.localizationService = localizationService;
    }

    @Override
    public User askUserName() {
        ioService.outln(localizationService.getLocalizationMessage("input.first.name"));
        String firstName = ioService.readString();
        ioService.outln(localizationService.getLocalizationMessage("input.last.name"));
        String lastName = ioService.readString();
        return new User(firstName, lastName);
    }

    @Override
    public void sayHelloToUser(User user) {
        ioService.outln(localizationService.getLocalizationMessage("hello.user", 
            user.getFirstName(), user.getLastName()));
        ioService.outln(localizationService.getLocalizationMessage("start.testing"));
    }

    @Override
    public UserQuizResult askQuestionsToUser(List<Question> questions) {
        int userCorrectAnswersNumber = 0;
        for (Question question : questions) {
            askQuestionToUser(question);
            int userAnswer = getUserAnswer();
            if (quizResultService.isCorrectUserAnswer(question, userAnswer)) {
                userCorrectAnswersNumber++;
            }
        }
        return new UserQuizResult(userCorrectAnswersNumber);
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
                ioService.outln(localizationService.getLocalizationMessage("wrong.answer.type"));
            }
        } while(!isCorrectInput);
        return userAnswer;
    }

    private void askQuestionToUser(Question question) {
        ioService.outln(String.format("\t[%s] - %s", question.getId(), question.getQuestion()));
        question.getAnswers().forEach(answer ->
            ioService.outln(String.format("\t\t[%s] - %s", answer.getId(), answer.getContent()))
        );
    }
}
