package com.tokmakov.hm03.models;

import lombok.Getter;

import java.util.List;

@Getter
public class Question {
    private final int id;
    private final String question;
    private final List<Answer> answers;

    public Question(int id, String question, List<Answer> answers) {
        this.id = id;
        this.question = question;
        this.answers = answers;
    }

    public boolean isCorrectAnswerById(int id) {
        return answers.stream().filter(f ->
            f.getId() == id).findFirst().get().isCorrectAnswer();
    }
}
