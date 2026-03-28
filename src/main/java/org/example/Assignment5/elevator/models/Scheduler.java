package org.example.Assignment5.elevator.models;

import org.example.Assignment5.elevator.requests.Request;
import org.example.Assignment5.elevator.strategies.SchedulingStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scheduler {
    private final List<Elevator> elevators;
    private final List<Request> requestQueue;
    private SchedulingStrategy strategy;

    public Scheduler(List<Elevator> elevators, SchedulingStrategy strategy) {
        this.elevators = elevators;
        this.requestQueue = new ArrayList<>();
        this.strategy = strategy;
    }

    public Elevator selectElevator(Request request) {
        requestQueue.add(request);
        Elevator selected = strategy.selectElevator(request, elevators);
        selected.addRequest(request);
        return selected;
    }

    public List<Elevator> getElevators() {
        return Collections.unmodifiableList(elevators);
    }

    public List<Request> getRequestQueue() {
        return Collections.unmodifiableList(requestQueue);
    }

    public void setStrategy(SchedulingStrategy strategy) {
        this.strategy = strategy;
    }
}
