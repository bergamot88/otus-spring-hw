package com.tokmakov.hm03.services;

import com.tokmakov.hm03.models.Question;

public interface QuizResultService {
    void showResult(int userCorrectAnswersNumber);
    boolean isCorrectUserAnswer(Question question, int userAnswer);
}
