package org.example.Assignment5.elevator.strategies;

import org.example.Assignment5.elevator.models.Elevator;
import org.example.Assignment5.elevator.requests.Request;

import java.util.List;

public interface SchedulingStrategy {
    Elevator selectElevator(Request request, List<Elevator> elevators);
}
