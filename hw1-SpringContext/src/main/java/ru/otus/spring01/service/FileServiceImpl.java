package ru.otus.spring01.service;

import ru.otus.spring01.domain.TestStep;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileServiceImpl implements FileService {
    private String testConfigName;

    public FileServiceImpl(String testConfigName) {
        this.testConfigName = testConfigName;
    }

    public String getTestConfigName() {
        return testConfigName;
    }

    public void setTestConfigName(String testConfigName) {
        this.testConfigName = testConfigName;
    }

    @Override
    public List<TestStep> readQuestions() {
        List<TestStep> testSteps = new ArrayList<>();
        try {
            Path path = Paths.get(ClassLoader.getSystemResource(testConfigName).toURI());
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
