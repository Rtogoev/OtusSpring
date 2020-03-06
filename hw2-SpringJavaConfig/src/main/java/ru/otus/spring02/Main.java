package ru.otus.spring02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring02.service.FileService;
import ru.otus.spring02.service.TestService;

import java.io.IOException;

@ComponentScan
@Configuration
@PropertySource("classpath:application.properties")
public class Main {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        FileService fileService = context.getBean(FileService.class);
        TestService testService = context.getBean(TestService.class);
        System.out.println(
                testService.execute(
                        fileService.readQuestions()
                )
        );
    }
}
