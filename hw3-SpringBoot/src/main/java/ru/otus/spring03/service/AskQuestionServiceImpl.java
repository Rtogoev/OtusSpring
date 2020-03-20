package ru.otus.spring03.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring03.domain.TestStep;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Service
public class AskQuestionServiceImpl implements AskQuestionService {
    private final MessageSource messageSource;
    private final KeyboardReaderService keyboardReaderService;

    public AskQuestionServiceImpl(MessageSource messageSource, KeyboardReaderService keyboardReaderService) {
        this.messageSource = messageSource;
        this.keyboardReaderService = keyboardReaderService;
    }

    @Override
    public int ask(List<TestStep> testSteps, Locale locale) throws IOException {

        int rightAnswersCount = 0;
        String testBegin = messageSource.getMessage("test.begin", null, locale);
        System.out.println(" =========================");
        System.out.println(" ======= " + testBegin + " ======");
        System.out.println(" =========================");

        for (TestStep testStep : testSteps) {
            System.out.println();
            System.out.println(testStep.getQuestion());
            System.out.println();

            List<String> answerVariants = testStep.getAnswerVariants();
            for (int i = 0; i < answerVariants.size(); i++) {
                String answerVariant = answerVariants.get(i);
                int answerVariantNumber = i + 1;
                System.out.print((answerVariantNumber + ")" + answerVariant + "\t"));
            }

            System.out.println();
            System.out.println();

            int actualAnswer = keyboardReaderService.readInt();
            if (actualAnswer == testStep.getRightVariant()) {
                rightAnswersCount++;
            }
        }

        String testEnd = messageSource.getMessage("test.end", null, locale);
        System.out.println(" =========================");
        System.out.println(" ======= " + testEnd + " ========");
        System.out.println(" =========================");

        return rightAnswersCount;
    }
}
