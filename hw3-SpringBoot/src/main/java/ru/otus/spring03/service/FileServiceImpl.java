package ru.otus.spring03.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring03.domain.TestStep;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class FileServiceImpl implements FileService {
    private final MessageSource messageSource;

    public FileServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public List<TestStep> readQuestions(Locale locale) {
        String questionsPath = messageSource.getMessage("questions.path", null, locale);
        List<TestStep> testSteps = new ArrayList<>();
        try {
            Path path = Paths.get(ClassLoader.getSystemResource(questionsPath).toURI());
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                while (reader.ready()) {
                    String[] split = reader.readLine().split(",");
                    List<String> answerVariants = new ArrayList<>();
                    for (int i = 1; i < split.length - 1; i++) {
                        answerVariants.add(split[i]);
                    }
                    testSteps.add(
                            new TestStep(
                                    split[0],
                                    answerVariants,
                                    Integer.parseInt(split[split.length - 1])
                            )
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return testSteps;
    }
}
