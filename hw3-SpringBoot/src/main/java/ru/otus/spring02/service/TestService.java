package ru.otus.spring02.service;

import ru.otus.spring02.domain.TestStep;

import java.io.IOException;
import java.util.List;

public interface TestService {
    String execute(List<TestStep> testSteps) throws IOException;
}
