package ru.otus.spring04.service;

import java.io.IOException;

public interface KeyboardReaderService {
    String readString() throws IOException;
    int readInt() throws IOException;
}
