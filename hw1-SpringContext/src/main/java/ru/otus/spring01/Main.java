package ru.otus.spring01;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring01.service.FileService;
import ru.otus.spring01.service.TestService;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        FileService fileService = context.getBean(FileService.class);
        TestService testService = context.getBean(TestService.class);
        System.out.println(
                testService.execute(
                        fileService.readQuestions()
                )
        );
    }
}
