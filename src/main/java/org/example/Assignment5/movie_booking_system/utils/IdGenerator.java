package org.example.Assignment5.movie_booking_system.utils;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
    private static final AtomicLong COUNTER = new AtomicLong(1000);

    private IdGenerator() {
    }

    public static String nextId(String prefix) {
        return prefix + "-" + COUNTER.incrementAndGet();
    }
}
