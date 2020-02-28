package ru.otus.spring01.service;

import ru.otus.spring01.domain.TestStep;

import java.util.List;

public class FileServiceImpl implements FileService {
    private String testConfigName;

    public void setTestConfigName(String testConfigName) {
        this.testConfigName = testConfigName;
    }

    public String getTestConfigName() {
        return testConfigName;
    }

    @Override
    public List<TestStep> readQuestions() {

        return null;
    }
}
