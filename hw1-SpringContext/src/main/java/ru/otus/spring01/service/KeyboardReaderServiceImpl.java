package ru.otus.spring01.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KeyboardReaderServiceImpl implements KeyboardReaderService {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public String readString() throws IOException {
        return reader.readLine();
    }

    @Override
    public int readInt() throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
