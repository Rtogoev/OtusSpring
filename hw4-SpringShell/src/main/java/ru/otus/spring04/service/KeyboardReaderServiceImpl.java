package ru.otus.spring04.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
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
