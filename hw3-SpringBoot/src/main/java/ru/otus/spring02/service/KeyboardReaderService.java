package ru.otus.spring02.service;

import java.io.IOException;

public interface KeyboardReaderService {
    String readString() throws IOException;
    int readInt() throws IOException;
}
