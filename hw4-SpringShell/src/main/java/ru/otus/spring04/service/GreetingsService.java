package ru.otus.spring04.service;

import ru.otus.spring04.domain.Credentials;

import java.io.IOException;
import java.util.Locale;

public interface GreetingsService {
    Credentials greet(Locale locale) throws IOException;
}
