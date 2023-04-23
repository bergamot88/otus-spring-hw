package com.tokmakov.hw02.models;

import java.util.List;

public class Question {
    private final int id;
    private final String question;
    private final List<Answer> answers;

    public Question(int id, String question, List<Answer> answers) {
        this.id = id;
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public int getId() {
        return id;
    }

    public boolean isCorrectAnswerById(int id) {
        return answers.stream().filter(f ->
            f.getId() == id).findFirst().get().isCorrectAnswer();
    }
}
