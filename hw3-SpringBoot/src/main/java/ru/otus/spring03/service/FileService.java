package ru.otus.spring03.service;

import ru.otus.spring03.domain.TestStep;

import java.util.List;
import java.util.Locale;


public interface FileService {
    List<TestStep> readQuestions(Locale locale);
}
