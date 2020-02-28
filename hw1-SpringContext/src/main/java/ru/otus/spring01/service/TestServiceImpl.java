package ru.otus.spring01.service;

import ru.otus.spring01.domain.TestStep;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class TestServiceImpl implements TestService {
    public TestServiceImpl() {
    }

    @Override
    public String startTest(List<TestStep> testSteps) {
        String name = null;
        String surname = null;
        int rightAnswersCount = 0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            // todo заменить на кередентиалс
            System.out.println("WHAT IS YOUR NAME?");
            name = reader.readLine();
            System.out.println("WHAT IS YOUR SURNAME?");
            surname = reader.readLine();

            System.out.println(" =========================");
            System.out.println(" ======= TEST START ======");
            System.out.println(" =========================");

            for (TestStep testStep : testSteps) {
                // todo refactor to StringFormatUtils!!!
                System.out.println(testStep.getQuestion());
                System.out.println();
                List<String> answerVariants = testStep.getAnswerVariants();
                for (int i = 0; i < answerVariants.size(); i++) {
                    String answerVariant = answerVariants.get(i);
                    System.out.println(i + ")" + answerVariant + "\t");
                }
                System.out.println();

                int actualAnser = Integer.parseInt(reader.readLine());
                if (actualAnser == testStep.getRightVariant()) {
                    rightAnswersCount++;
                }
            }

            System.out.println(" =========================");
            System.out.println(" ======= TEST END ========");
            System.out.println(" =========================");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Dir " + name + " " + surname + ",you answered right " + rightAnswersCount + " times!";
    }
}
