package ru.otus.spring01.service;

import ru.otus.spring01.domain.Credentials;
import ru.otus.spring01.domain.TestStep;

import java.io.IOException;
import java.util.List;

public class TestServiceImpl implements TestService {

    private final GreetingsService greetingsService;
    private final AskQuestionService askQuestionService;

    public TestServiceImpl(GreetingsService greetingsService, AskQuestionService askQuestionService) {
        this.greetingsService = greetingsService;
        this.askQuestionService = askQuestionService;
    }

    @Override
    public String execute(List<TestStep> testSteps) throws IOException {
        Credentials credentials = greetingsService.greet();
        int rightAnswersCount = askQuestionService.ask(testSteps);
        return "Dir " +
                credentials.getName() + " " +
                credentials.getSurname() +
                ",you answered right " + rightAnswersCount +
                " times!";
    }
}
