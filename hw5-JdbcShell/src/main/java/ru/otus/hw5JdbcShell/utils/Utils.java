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

    public static String format(List<Long> longList) {
        if (longList == null) {
            return "()";
        }
        if (longList.size() == 0) {
            return "()";
        }

        StringBuilder builder = new StringBuilder("(")
                .append(longList.get(0));

        for (int i = 1; i < longList.size(); i++) {
            builder.append(",")
                    .append(longList.get(i));
        }
        builder.append(")");
        return builder.toString();
    }
}
