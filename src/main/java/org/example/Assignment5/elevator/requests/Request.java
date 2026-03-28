package org.example.Assignment5.elevator.requests;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Request {
    private static final AtomicInteger REQUEST_SEQ = new AtomicInteger(0);

    private final int requestId;
    private final int floor;
    private final LocalDateTime timestamp;

    protected Request(int floor) {
        this.requestId = REQUEST_SEQ.incrementAndGet();
        this.floor = floor;
        this.timestamp = LocalDateTime.now();
    }

    public int getRequestId() {
        return requestId;
    }

    public int getFloor() {
        return floor;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
