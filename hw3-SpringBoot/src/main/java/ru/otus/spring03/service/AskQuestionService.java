package ru.otus.spring03.service;


import ru.otus.spring03.domain.TestStep;

import java.io.IOException;
import java.util.List;

public interface AskQuestionService {
   int ask(List<TestStep> testSteps) throws IOException;
}
