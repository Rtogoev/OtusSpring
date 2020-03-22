package ru.otus.spring04.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring04.domain.Credentials;

import java.io.IOException;
import java.util.Locale;

@Service
public class GreetingsServiceImpl implements GreetingsService {
    private final KeyboardReaderService keyboardReaderService;
    private final MessageSource messageSource;

    public GreetingsServiceImpl(KeyboardReaderService keyboardReaderService, MessageSource messageSource) {
        this.keyboardReaderService = keyboardReaderService;
        this.messageSource = messageSource;
    }

    @Override
    public Credentials greet(Locale locale) throws IOException {
        System.out.println(
                messageSource.getMessage(
                        "ask.name",
                        null,
                        locale
                )
        );
        String name = keyboardReaderService.readString();

        System.out.println(
                messageSource.getMessage(
                        "ask.surname",
                        null,
                        locale
                )
        );
        String surName = keyboardReaderService.readString();

        return new Credentials(
                name,
                surName
        );
    }
}
