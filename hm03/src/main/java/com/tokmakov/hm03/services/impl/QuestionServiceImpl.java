package com.tokmakov.hm03.services.impl;

import com.tokmakov.hm03.dao.QuizDao;
import com.tokmakov.hm03.exceptions.SourceReadException;
import com.tokmakov.hm03.models.Question;
import com.tokmakov.hm03.services.IOService;
import com.tokmakov.hm03.services.LocalizationService;
import com.tokmakov.hm03.services.QuestionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuizDao quizDao;
    private final IOService ioService;
    private final LocalizationService localizationService;

    public QuestionServiceImpl(QuizDao quizDao, 
                               IOService ioService,
                               LocalizationService localizationService) {
        this.quizDao = quizDao;
        this.ioService = ioService;
        this.localizationService = localizationService;
    }

    @Override
    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        try {
            questions = quizDao.findAllQuestions();
        } catch (SourceReadException ex) {
            ioService.outln(
                localizationService.getLocalizationMessage("exception.read.questions.from.source")
            );
        }
        return questions;
    }
}
