package com.tokmakov.hm03.config;

import java.util.Locale;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.yml")
public class ApplcationConfigProperties implements ConfigProperties {
    private final int correctAnswersNumber;
    private final String csvFileWithQuestionsName;
    private Locale locale;

    public ApplcationConfigProperties(Environment env) {
        this.correctAnswersNumber = Integer.parseInt(env.getProperty("correct.answers"));
        this.csvFileWithQuestionsName = env.getProperty("csv.file.name");
        this.locale = new Locale(env.getProperty("locale"));
    }

    @Override
    public int getCorrectAnswersNumber() {
        return correctAnswersNumber;
    }

    @Override
    public String getCSVFileWithQuestionsName() {
        String fileName = String.format("%s_%s.csv", csvFileWithQuestionsName, locale.getLanguage());
        return fileName;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }
}
