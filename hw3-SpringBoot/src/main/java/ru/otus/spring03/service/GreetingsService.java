package ru.otus.spring03.service;

import ru.otus.spring03.domain.Credentials;

import java.io.IOException;
import java.util.Locale;

public interface GreetingsService {
    Credentials greet(Locale locale) throws IOException;
}
