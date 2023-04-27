package com.tokmakov.hm03.dao;

import java.util.List;

import com.tokmakov.hm03.exceptions.SourceReadException;
import com.tokmakov.hm03.models.Question;

public interface QuizDao {
    List<Question> findAllQuestions() throws SourceReadException;
}
