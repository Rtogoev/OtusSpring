package ru.otus.hw5JdbcShell.utils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilsTest {

    @Test
    void format_null() {
        assertEquals("()", Utils.format(null));
    }

    @Test
    void format_empty() {
        assertEquals("()", Utils.format(Collections.emptyList()));
    }

    @Test
    void format_single() {
        assertEquals("(1)", Utils.format(Collections.singletonList(1L)));
    }

    @Test
    void format() {
        List<Long> longList = new ArrayList<>();
        longList.add(1L);
        longList.add(2L);
        longList.add(3L);
        assertEquals("(1,2,3)", Utils.format(longList));
    }
}