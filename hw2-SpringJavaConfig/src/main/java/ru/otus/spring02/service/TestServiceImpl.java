package ru.otus.spring02.service;

import org.springframework.stereotype.Service;
import ru.otus.spring02.domain.Credentials;
import ru.otus.spring02.domain.TestStep;

import java.io.IOException;
import java.util.List;

@Service
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
