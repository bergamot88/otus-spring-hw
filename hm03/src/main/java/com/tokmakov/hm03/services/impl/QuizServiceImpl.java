package com.tokmakov.hm03.services.impl;

import com.tokmakov.hm03.models.Question;
import com.tokmakov.hm03.models.User;
import com.tokmakov.hm03.models.UserQuizResult;
import com.tokmakov.hm03.services.QuestionService;
import com.tokmakov.hm03.services.QuizResultService;
import com.tokmakov.hm03.services.QuizService;
import com.tokmakov.hm03.services.UserDialogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {
    
    private final QuestionService questionService;
    private final UserDialogService userDialogService;
    private final QuizResultService quizResultService;

    public QuizServiceImpl(QuestionService questionService,
                            UserDialogService userDialogService,
                            QuizResultService quizResultService) {
        this.questionService = questionService;
        this.userDialogService = userDialogService;
        this.quizResultService = quizResultService;
    }

    @Override
    public void runQuiz() {
        List<Question> questions = questionService.getQuestions();
        User user = userDialogService.askUserName();
        userDialogService.sayHelloToUser(user);
        UserQuizResult userQuizResult = userDialogService.askQuestionsToUser(questions);
        quizResultService.showResult(userQuizResult.getNumberRightQuestions());
    }
}
