package ru.otus.spring04.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring04.domain.Credentials;
import ru.otus.spring04.domain.TestStep;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class TestServiceImpl implements TestService {

    private static final Map<String, Locale> locales = new HashMap<>();
    private final MessageSource messageSource;
    private final GreetingsService greetingsService;
    private final AskQuestionService askQuestionService;
    private final FileService fileService;
    private final String localeName;

    public TestServiceImpl(
            MessageSource messageSource, GreetingsService greetingsService,
            AskQuestionService askQuestionService,
            FileService fileService,
            @Value("${locale}") String localeName
    ) {
        this.messageSource = messageSource;
        this.greetingsService = greetingsService;
        this.askQuestionService = askQuestionService;
        this.fileService = fileService;
        this.localeName = localeName;
        locales.put("ru", new Locale("ru","RU"));
        locales.put("en", new Locale("en","US"));
    }

    @Override
    public void execute() throws IOException {
        Locale locale = locales.get(localeName);
        if (localeName.equals("en")) {
            locale = new Locale("en", "US");
        }
        Credentials credentials = greetingsService.greet(locale);

        List<TestStep> testSteps = fileService.readQuestions(locale);
        int rightAnswersCount = askQuestionService.ask(testSteps, locale);

        System.out.println();
        String result = messageSource.getMessage(
                "result",
                new String[]{
                        credentials.getName() + " " + credentials.getSurname(),
                        String.valueOf(rightAnswersCount)
                },
                locale
        );
        System.out.println(result);
        System.out.println();
    }
}
