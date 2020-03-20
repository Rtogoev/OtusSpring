package ru.otus.spring02.service;

import org.springframework.stereotype.Service;
import ru.otus.spring02.domain.TestStep;

import java.util.List;


public interface FileService {
    List<TestStep> readQuestions();
}
