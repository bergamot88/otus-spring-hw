package com.tokmakov.hm03.dao;

import com.tokmakov.hm03.models.Question;

import java.util.List;

public interface QuizDao {
    List<Question> findAllQuestions();
}
