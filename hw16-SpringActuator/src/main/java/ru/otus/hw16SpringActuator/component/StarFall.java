package ru.otus.hw16SpringActuator.component;

import java.time.LocalDateTime;

public class StarFall {

    public static final String ANSI_RESET = "\u001B[0m";

    private final static String[] COLORS = new String[]{
            "\u001B[30m",
            "\u001B[31m",
            "\u001B[32m",
            "\u001B[33m",
            "\u001B[34m",
            "\u001B[35m",
            "\u001B[36m",
            "\u001B[37m"
    };
    private static final int MAX_LINE_SIZE = 100;

    public static void starFall() {
        process();
    }

    private static boolean generateBoolean() {
        return Math.random() > 0.4 + (double) getCurrentSecond() / 100;
    }

    private static int getCurrentSecond() {
        return LocalDateTime.now().getSecond();
    }

    private static void process() {
            for (int part = 0; part < MAX_LINE_SIZE; part++) {
                System.out.print(
                        dotOrSpace(
                                generateBoolean()
                        )
                );
            }
            System.out.println();
    }

    private static String dotOrSpace(boolean dot) {
        if (dot) {
            return randomColor() + "*" + ANSI_RESET;
        }
        return " ";
    }

    private static String randomColor() {
        return COLORS[(int) (Math.random() * COLORS.length)];
    }
}