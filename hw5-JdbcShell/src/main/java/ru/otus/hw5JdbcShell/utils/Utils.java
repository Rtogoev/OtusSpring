package ru.otus.hw5JdbcShell.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Utils {
    public static Set<Long> toLongSet(Object longArrayObject) {
        Object[] longObjectArray = (Object[]) longArrayObject;
        Set<Long> longSet = new HashSet<>();
        for (Object longObject : longObjectArray) {
            longSet.add((Long) longObject);
        }
        return longSet;
    }

    public static Set<String> convert(String commaSeparatedString) {
        return new HashSet<>(
                Arrays.asList(
                        commaSeparatedString.split(",")
                )
        );
    }
}