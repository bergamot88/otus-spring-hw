package com.tokmakov.hm03.services;

public interface LocalizationService {
    String getLocalizationMessage(String property);
    String getLocalizationMessage(String property, Object... args);
}
