package ru.otus.spring04.service;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.spring04.domain.TestStep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class FileServiceImplTest {

    private final static List<TestStep> expectedTestSteps = new ArrayList<>();
    private FileService fileService;

    @Before
    public void setUp() {
        fileService = new FileServiceImpl(messageSource());
        expectedTestSteps.add(new TestStep("Question1", Arrays.asList("variant1", "variant2", "variant3"), 1));
        expectedTestSteps.add(new TestStep("Question2", Arrays.asList("variant1", "variant2", "variant3", "variant4"), 2));
        expectedTestSteps.add(new TestStep("Question3", Arrays.asList("variant1", "variant2", "variant3", "variant4", "variant5"), 3));
        expectedTestSteps.add(new TestStep("Question4", Arrays.asList("variant1", "variant2", "variant3", "variant4", "variant5", "variant6"), 4));
        expectedTestSteps.add(new TestStep("Question5", Arrays.asList("variant1", "variant2", "variant3", "variant4", "variant5", "variant6", "variant7"), 5));
    }

    @Test
    public void readQuestions() {
        List<TestStep> actualTestSteps = fileService.readQuestions(new Locale("en", "US"));
        Assert.assertEquals(expectedTestSteps, actualTestSteps);
    }

    private MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/messages");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }
}