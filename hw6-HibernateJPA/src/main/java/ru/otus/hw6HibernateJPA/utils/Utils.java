package ru.otus.hw6HibernateJPA.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Utils {
    public static Set<Long> toLongSet(Object longArrayObject) {
        Object[] longObjectArray = (Object[]) longArrayObject;
        Set<Long> longSet = new HashSet<>();
        for (Object longObject : longObjectArray) {
            longSet.add((Long) longObject);
        }
        return longSet;
    }

    public static long generateLong(){
        return 1 - UUID.randomUUID().getLeastSignificantBits();
    }

    public static Set<String> split(String commaSeparatedString) {
        return new HashSet<>(
                Arrays.asList(
                        commaSeparatedString.split(",")
                )
        );
    }
}