package com.tokmakov.hm03.models;

import lombok.Getter;

@Getter
public class UserQuizResult {
    private final int numberRightQuestions;

    public UserQuizResult(int numberRightQuestions) {
        this.numberRightQuestions = numberRightQuestions;
    }
}
