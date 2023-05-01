package com.tokmakov.hm03.dao;

import com.tokmakov.hm03.exceptions.SourceReadException;
import com.tokmakov.hm03.models.Answer;
import com.tokmakov.hm03.models.Question;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class QuizDaoFromFileImpl implements QuizDao {

    private final String csvFileWithQuestionsName;

    public QuizDaoFromFileImpl(@Value("${csv.file.name}") String csvFileWithQuestionsName) {
        this.csvFileWithQuestionsName = csvFileWithQuestionsName;
    }

    @Override
    public List<Question> findAllQuestions() {
        List<Question> questions = new ArrayList<>();
        InputStreamReader inputStreamReader = getInputStreamReaderForFile(csvFileWithQuestionsName);

        Pattern idPattern = Pattern.compile("^[0-9]");
        Pattern optionsPattern = Pattern.compile("option:([\"\'])?((?:.(?!\1|,))*.?)\1?");
        Pattern answPattern = Pattern.compile("answer:[0-9]");
        Pattern questionPattern = Pattern.compile("question:([\"\'])?((?:.(?!\1|,))*.?)\1?");
        String line;

        try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            while ((line = bufferedReader.readLine()) != null) {
                int answerId = 0;
                String question = "";
                int correctAnswer = 0;
                List<Answer> options = new ArrayList<Answer>();
                Matcher answerMatcher = answPattern.matcher(line);
                Matcher optionMatcher = optionsPattern.matcher(line);
                Matcher idMatcher = idPattern.matcher(line);
                Matcher questionMatcher = questionPattern.matcher(line);
                while (idMatcher.find()) {
                    answerId = Integer.parseInt(idMatcher.group());
                }
                while (questionMatcher.find()) {
                    question = questionMatcher.group().replace("question:", "");
                }
                while (answerMatcher.find()) {
                    correctAnswer = Integer.parseInt(answerMatcher.group().replace("answer:", ""));
                }
                int optionId = 1;
                while (optionMatcher.find()) {
                    boolean isCorrect = optionId == correctAnswer;
                    options.add(new Answer(optionId, 
                        optionMatcher.group().replace("option:", ""), isCorrect));
                    optionId++;
                }
                questions.add(new Question(answerId, question, options));
            }
        } catch (IOException e) {
            throw new SourceReadException("An error occurs when trying to read a file: " +
                csvFileWithQuestionsName, e);
        }
        return questions;
    }

    private InputStreamReader getInputStreamReaderForFile(String fileName) {
        InputStreamReader inputStreamReader;
        InputStream inputStream = getInputStreamForFile(fileName);
        try {
            inputStreamReader = new InputStreamReader(inputStream);
        } catch (NullPointerException e) {
            throw new SourceReadException("Couldn't read the file: " + csvFileWithQuestionsName, e);
        }
        return inputStreamReader;
    }

    private InputStream getInputStreamForFile(String fileName) {
        InputStream inputStream;
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(csvFileWithQuestionsName);
        } catch (NullPointerException e) {
            throw new SourceReadException("Questions file not found: " + csvFileWithQuestionsName, e);
        }
        return inputStream;
    }
}
