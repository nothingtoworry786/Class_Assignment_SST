package org.example.Assignment5.elevator.strategies;

import org.example.Assignment5.elevator.enums.ElevatorState;
import org.example.Assignment5.elevator.models.Elevator;
import org.example.Assignment5.elevator.requests.ExternalRequest;
import org.example.Assignment5.elevator.requests.Request;

import java.util.Comparator;
import java.util.List;

public class NearestCarStrategy implements SchedulingStrategy {
    @Override
    public Elevator selectElevator(Request request, List<Elevator> elevators) {
        if (elevators == null || elevators.isEmpty()) {
            throw new IllegalArgumentException("No elevators configured");
        }

        if (request instanceof ExternalRequest externalRequest) {
            return elevators.stream()
                    .min(Comparator
                            .comparingInt((Elevator e) -> score(e, externalRequest))
                            .thenComparingInt(Elevator::getId))
                    .orElse(elevators.get(0));
        }

        return elevators.stream()
                .min(Comparator
                        .comparingInt((Elevator e) -> Math.abs(e.getCurrentFloor() - request.getFloor()))
                        .thenComparingInt(Elevator::getId))
                .orElse(elevators.get(0));
    }

    private int score(Elevator elevator, ExternalRequest request) {
        int distance = Math.abs(elevator.getCurrentFloor() - request.getFloor());
        if (elevator.getState() == ElevatorState.IDLE) {
            return distance;
        }

        boolean aligned = elevator.getDirection() == request.getDirection();
        return aligned ? distance : distance + 100;
    }
}
