package ru.otus.spring03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
@Configuration
public class Spring03Application {

    public static void main(String[] args) {
        SpringApplication.run(Spring03Application.class, args);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/messages");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }
}