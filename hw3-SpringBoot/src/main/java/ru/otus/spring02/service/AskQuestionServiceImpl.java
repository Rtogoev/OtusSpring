package ru.otus.spring02.service;

import org.springframework.stereotype.Service;
import ru.otus.spring02.domain.TestStep;

import java.io.IOException;
import java.util.List;

@Service
public class AskQuestionServiceImpl implements AskQuestionService {

    private final KeyboardReaderService keyboardReaderService;

    public AskQuestionServiceImpl(KeyboardReaderService keyboardReaderService) {
        this.keyboardReaderService = keyboardReaderService;
    }

    @Override
    public int ask(List<TestStep> testSteps) throws IOException {

        int rightAnswersCount = 0;

        System.out.println(" =========================");
        System.out.println(" ======= TEST START ======");
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

        System.out.println(" =========================");
        System.out.println(" ======= TEST END ========");
        System.out.println(" =========================");

        return rightAnswersCount;
    }
}
