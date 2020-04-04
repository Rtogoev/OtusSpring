package ru.otus.spring04.domain;

import java.util.List;
import java.util.Objects;

public class TestStep {
    private final String question;
    private final List<String> answerVariants;
    private final int rightVariant;

    public TestStep(String question, List<String> answerVariants, int rightVariant) {
        this.question = question;
        this.answerVariants = answerVariants;
        this.rightVariant = rightVariant;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswerVariants() {
        return answerVariants;
    }

    public int getRightVariant() {
        return rightVariant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestStep)) return false;
        TestStep testStep = (TestStep) o;
        return rightVariant == testStep.rightVariant &&
                Objects.equals(question, testStep.question) &&
                Objects.equals(answerVariants, testStep.answerVariants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answerVariants, rightVariant);
    }

    @Override
    public String toString() {
        return "TestStep{" +
                "question='" + question + '\'' +
                ", answerVariants=" + answerVariants +
                ", rightVariant=" + rightVariant +
                '}';
    }
}
