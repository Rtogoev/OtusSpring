package ru.otus.spring01.service;

import ru.otus.spring01.domain.Credentials;

import java.io.IOException;

public class GreetingsServiceImpl implements GreetingsService {
    private final KeyboardReaderService keyboardReaderService;

    public GreetingsServiceImpl(KeyboardReaderService keyboardReaderService) {
        this.keyboardReaderService = keyboardReaderService;
    }

    @Override
    public Credentials greet() throws IOException {

        System.out.println("WHAT IS YOUR NAME?");
        String name = keyboardReaderService.readString();

        System.out.println("WHAT IS YOUR SURNAME?");
        String surName = keyboardReaderService.readString();

        return new Credentials(
                name,
                surName
        );
    }
}
