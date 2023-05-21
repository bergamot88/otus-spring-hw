package com.tokmakov.hm03.config;

import java.util.Locale;

public interface ConfigProperties {
    String getCSVFileWithQuestionsName();
    int getCorrectAnswersNumber();
    Locale getLocale();
}
