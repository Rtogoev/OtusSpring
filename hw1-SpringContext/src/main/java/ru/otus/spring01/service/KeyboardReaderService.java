package ru.otus.spring01.service;

import java.io.IOException;

public interface KeyboardReaderService {
    String readString() throws IOException;
    int readInt() throws IOException;
}
