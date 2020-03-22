package ru.otus.spring04.service;


import ru.otus.spring04.domain.TestStep;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public interface AskQuestionService {
   int ask(List<TestStep> testSteps, Locale locale) throws IOException;
}
