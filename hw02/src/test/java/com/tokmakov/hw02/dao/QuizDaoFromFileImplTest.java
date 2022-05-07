package com.tokmakov.hw02.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tokmakov.hw02.exceptions.SourceReadException;

public class QuizDaoFromFileImplTest {
    private QuizDao quizDao;

    private final static String EXIST_FILE_CSV_NAME = "test_questions.csv";
    private final static String NOT_EXIST_FILE_CSV_NAME = "not_existing.csv";

    private static final String FIRST_QUESTION_CONTAIN = "What is java1?";
    private static final String SECOND_QUESTION_CONTAIN = "What is java2?";

    private static final String OPTION_1_CONTAIN = "programming language";
    private static final String OPTION_2_CONTAIN = "car";
    private static final String OPTION_3_CONTAIN = "planet";

    private static final String ANSWER_CONTAIN = "programming language";

    @BeforeEach
    void setUp() {
        quizDao = new QuizDaoFromFileImpl(EXIST_FILE_CSV_NAME);
    }

    @Test
    void checkQuestionsContent() throws SourceReadException {
        assertEquals(FIRST_QUESTION_CONTAIN, 
            quizDao.findAllQuestions().get(0).getQuestion());
        assertEquals(SECOND_QUESTION_CONTAIN, 
            quizDao.findAllQuestions().get(1).getQuestion());
    }

    @Test
    void checkOptionsContent() throws SourceReadException {
        assertEquals(OPTION_1_CONTAIN, 
            quizDao.findAllQuestions().get(0).getAnswers().get(0).getContent());
        assertEquals(OPTION_2_CONTAIN, 
            quizDao.findAllQuestions().get(0).getAnswers().get(1).getContent());
        assertEquals(OPTION_3_CONTAIN, 
            quizDao.findAllQuestions().get(0).getAnswers().get(2).getContent());
    }

    @Test
    void checkAnswerContent() throws SourceReadException {
        assertEquals(ANSWER_CONTAIN, 
            quizDao.findAllQuestions().get(0).getAnswers().get(0).getContent());
    }

    @Test
    void checkThatFindAllQuestionsMethodThrowSourceReadException() throws SourceReadException {
        QuizDao quizDao1 = new QuizDaoFromFileImpl(NOT_EXIST_FILE_CSV_NAME);
        assertThrows(SourceReadException.class, quizDao1::findAllQuestions);
    }
}
