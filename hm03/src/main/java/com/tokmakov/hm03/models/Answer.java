package com.tokmakov.hm03.models;

public class Answer {
    private final int id;
    private final boolean isCorrectAnswer;
    private final String content;

    public Answer(int id, String content, boolean isCorrectAnswer) {
        this.id = id;
        this.isCorrectAnswer = isCorrectAnswer;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public boolean isCorrectAnswer() {
        return isCorrectAnswer;
    }

    public String getContent() {
        return content;
    }
}
