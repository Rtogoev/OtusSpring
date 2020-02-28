package ru.otus.spring01.service;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring01.Main;
import ru.otus.spring01.domain.TestStep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class FileServiceImplTest {

    private final static List<TestStep> expectedTestSteps = new ArrayList<>();
    private FileService fileService;

    @Before
    public void setUp() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        fileService = context.getBean(FileService.class);
        expectedTestSteps.add(new TestStep("Question1", Arrays.asList("variant1", "variant2", "variant3"), 1));
        expectedTestSteps.add(new TestStep("Question2", Arrays.asList("variant1", "variant2", "variant3", "variant4"), 2));
        expectedTestSteps.add(new TestStep("Question3", Arrays.asList("variant1", "variant2", "variant3", "variant4", "variant5"), 3));
        expectedTestSteps.add(new TestStep("Question4", Arrays.asList("variant1", "variant2", "variant3", "variant4", "variant5", "variant6"), 4));
        expectedTestSteps.add(new TestStep("Question5", Arrays.asList("variant1", "variant2", "variant3", "variant4", "variant5", "variant6", "variant7"), 5));
    }

    @Test
    public void readQuestions() {
        List<TestStep> actualTestSteps = fileService.readQuestions();
        assertEquals(expectedTestSteps, actualTestSteps);
    }
}