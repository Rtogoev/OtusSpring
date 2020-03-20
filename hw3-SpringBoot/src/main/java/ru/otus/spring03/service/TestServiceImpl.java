package ru.otus.spring03.service;

import org.springframework.stereotype.Service;
import ru.otus.spring03.domain.Credentials;
import ru.otus.spring03.domain.TestStep;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    private final GreetingsService greetingsService;
    private final AskQuestionService askQuestionService;
    private final FileService fileService;

    public TestServiceImpl(
            GreetingsService greetingsService,
            AskQuestionService askQuestionService,
            FileService fileService
    ) {
        this.greetingsService = greetingsService;
        this.askQuestionService = askQuestionService;
        this.fileService = fileService;
    }

    @PostConstruct
    void init() throws IOException {
        execute();
    }

    @Override
    public void execute() throws IOException {
        Credentials credentials = greetingsService.greet();

        List<TestStep> testSteps = fileService.readQuestions();
        int rightAnswersCount = askQuestionService.ask(testSteps);

        System.out.println();
        System.out.println(
                "Dir " +
                        credentials.getName() + " " +
                        credentials.getSurname() +
                        ",you answered right " + rightAnswersCount +
                        " times!"
        );
        System.out.println();
    }
}
