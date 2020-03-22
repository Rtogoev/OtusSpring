package ru.otus.spring04.service;

import ru.otus.spring04.domain.TestStep;

import java.util.List;
import java.util.Locale;


public interface FileService {
    List<TestStep> readQuestions(Locale locale);
}
