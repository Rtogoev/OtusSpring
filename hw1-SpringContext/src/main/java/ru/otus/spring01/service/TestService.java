package ru.otus.spring01.service;

import ru.otus.spring01.domain.TestStep;

import java.io.IOException;
import java.util.List;

public interface TestService {
    String execute(List<TestStep> testSteps) throws IOException;
}
