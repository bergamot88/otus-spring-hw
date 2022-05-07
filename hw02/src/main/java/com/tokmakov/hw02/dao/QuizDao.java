package com.tokmakov.hw02.dao;

import java.util.List;

import com.tokmakov.hw02.exceptions.SourceReadException;
import com.tokmakov.hw02.models.Question;

public interface QuizDao {
    List<Question> findAllQuestions() throws SourceReadException;
}
