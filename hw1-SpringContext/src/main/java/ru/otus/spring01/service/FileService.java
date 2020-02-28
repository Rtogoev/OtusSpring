package ru.otus.spring01.service;

import ru.otus.spring01.domain.TestStep;

import java.util.List;

public interface FileService {
    List<TestStep> readQuestions();
}
