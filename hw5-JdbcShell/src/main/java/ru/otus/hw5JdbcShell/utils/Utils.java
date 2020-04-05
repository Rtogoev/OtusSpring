package ru.otus.hw5JdbcShell.utils;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<Long> toLongList(Object longArrayObject) {
        Object[] longObjectArray = (Object[]) longArrayObject;
        List<Long> longList = new ArrayList<>();
        for (Object longObject : longObjectArray) {
            longList.add((Long) longObject);
        }
        return longList;
    }
}
