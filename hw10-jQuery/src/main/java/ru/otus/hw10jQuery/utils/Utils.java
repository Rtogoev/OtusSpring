package ru.otus.hw10jQuery.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Utils {

    public static Set<String> split(String commaSeparatedString) {
        return new HashSet<>(
                Arrays.asList(
                        commaSeparatedString.split(",")
                )
        );
    }
}