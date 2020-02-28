package ru.otus.spring01.domain;

import java.util.Arrays;
import java.util.Objects;

public class TestStep {
    private String question;
    private String[] answerVariants;
    private int rightVariant;

    public TestStep(String question, String[] answerVariants, int rightVariant) {
        this.question = question;
        this.answerVariants = answerVariants;
        this.rightVariant = rightVariant;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getAnswerVariants() {
        return answerVariants;
    }

    public void setAnswerVariants(String[] answerVariants) {
        this.answerVariants = answerVariants;
    }

    public int getRightVariant() {
        return rightVariant;
    }

    public void setRightVariant(int rightVariant) {
        this.rightVariant = rightVariant;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", answerVariants=" + Arrays.toString(answerVariants) +
                ", rightVariant=" + rightVariant +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestStep)) return false;
        TestStep testStep1 = (TestStep) o;
        return rightVariant == testStep1.rightVariant &&
                Objects.equals(question, testStep1.question) &&
                Arrays.equals(answerVariants, testStep1.answerVariants);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(question, rightVariant);
        result = 31 * result + Arrays.hashCode(answerVariants);
        return result;
    }
}
