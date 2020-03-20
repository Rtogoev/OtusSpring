package ru.otus.spring03.service;

import ru.otus.spring03.domain.Credentials;

import java.io.IOException;

public interface GreetingsService {
    Credentials greet() throws IOException;
}
