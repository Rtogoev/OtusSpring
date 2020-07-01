package ru.otus.hw12Security.utils;

import ru.otus.hw12Security.model.User;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Utils {

    public static Set<String> split(String commaSeparatedString) {
        return new HashSet<>(
                Arrays.asList(
                        commaSeparatedString.split(",")
                )
        );
    }

    public static Set<User> toUserSet(String[] users) {
        return Arrays.stream(users)
                .map(
                        user -> {
                            String[] split = user.split(":");
                            return new User(
                                    split[0],
                                    split[1]
                            );
                        }
                ).collect(Collectors.toSet());
    }
}