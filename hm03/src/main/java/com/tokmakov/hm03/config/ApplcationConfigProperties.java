package com.tokmakov.hm03.config;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.yml")
public class ApplcationConfigProperties implements ConfigProperties {
    private final int correctAnswersNumber;
    private final String csvFileWithQuestionsName;

    public ApplcationConfigProperties(Environment env) {
        this.correctAnswersNumber = Integer.parseInt(env.getProperty("correct.answers"));
        this.csvFileWithQuestionsName = env.getProperty("csv.file.name");
    }

    @Override
    public int getCorrectAnswersNumber() {
        return correctAnswersNumber;
    }

    @Override
    public String getCSVFileWithQuestionsName() {
        return csvFileWithQuestionsName;
    }
}
