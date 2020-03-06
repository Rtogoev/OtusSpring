package ru.otus.spring02.service;

import ru.otus.spring02.domain.Credentials;

import java.io.IOException;

public interface GreetingsService {
    Credentials greet() throws IOException;
}
