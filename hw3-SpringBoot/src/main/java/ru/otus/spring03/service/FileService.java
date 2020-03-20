package ru.otus.spring03.service;

import ru.otus.spring03.domain.TestStep;

import java.util.List;


public interface FileService {
    List<TestStep> readQuestions();
}
