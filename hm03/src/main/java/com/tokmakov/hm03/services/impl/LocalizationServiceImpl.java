package com.tokmakov.hm03.services.impl;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.tokmakov.hm03.config.ConfigProperties;
import com.tokmakov.hm03.services.LocalizationService;

@Service
public class LocalizationServiceImpl implements LocalizationService {

    private final MessageSource messageSource;
    private final ConfigProperties quizProperties;

    public LocalizationServiceImpl(MessageSource messageSource, ConfigProperties quizProperties) {
        this.quizProperties = quizProperties;
        this.messageSource = messageSource;
    }

    @Override
    public String getLocalizationMessage(String property) {
        return this.messageSource.getMessage(property, null, quizProperties.getLocale());
    }

    @Override
    public String getLocalizationMessage(String property, Object... args) {
        return this.messageSource.getMessage(property, args, quizProperties.getLocale());
    }
    
}
