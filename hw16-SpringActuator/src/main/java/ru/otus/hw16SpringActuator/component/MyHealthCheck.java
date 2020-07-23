package ru.otus.hw16SpringActuator.component;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ru.otus.hw16SpringActuator.component.StarFall.*;

@Component
class FunnyHealthIndicator implements HealthIndicator {
    private final static Status[] STATUSES = new Status[]{
            Status.UNKNOWN,
            Status.UP,
            Status.DOWN,
            Status.OUT_OF_SERVICE
    };

    @Override
    public Health health() {
        starFall();
        return Health.status(STATUSES[(int) (Math.random() * STATUSES.length)]).build();
    }
}
