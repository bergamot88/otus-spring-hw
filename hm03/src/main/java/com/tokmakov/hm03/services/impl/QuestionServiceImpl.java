package com.tokmakov.hm03.services.impl;

import com.tokmakov.hm03.dao.QuizDao;
import com.tokmakov.hm03.exceptions.SourceReadException;
import com.tokmakov.hm03.models.Question;
import com.tokmakov.hm03.services.IOService;
import com.tokmakov.hm03.services.QuestionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    
    private final QuizDao quizDao;
    private final IOService ioService;

    public QuestionServiceImpl(QuizDao quizDao, IOService ioService) {
        this.quizDao = quizDao;
        this.ioService = ioService;
    }

    @Override
    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        try {
            questions = quizDao.findAllQuestions();
        } catch (SourceReadException ex) {
            ioService.outln(String.format("Could not read the questions from the source\n%s",
                ex.getMessage()));
        }
        return questions;
    }
}
