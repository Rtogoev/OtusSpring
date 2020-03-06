package ru.otus.spring01.service;

import ru.otus.spring01.domain.Credentials;

import java.io.IOException;

public interface GreetingsService {
    Credentials greet() throws IOException;
}
