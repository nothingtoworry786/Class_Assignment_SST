package org.example.Assignment5.elevator.strategies;

import org.example.Assignment5.elevator.enums.Direction;
import org.example.Assignment5.elevator.models.Elevator;
import org.example.Assignment5.elevator.requests.ExternalRequest;
import org.example.Assignment5.elevator.requests.Request;

import java.util.Comparator;
import java.util.List;

public class SCANStrategy implements SchedulingStrategy {
    @Override
    public Elevator selectElevator(Request request, List<Elevator> elevators) {
        if (elevators == null || elevators.isEmpty()) {
            throw new IllegalArgumentException("No elevators configured");
        }

        if (!(request instanceof ExternalRequest externalRequest)) {
            return elevators.stream()
                    .min(Comparator.comparingInt(e -> Math.abs(e.getCurrentFloor() - request.getFloor())))
                    .orElse(elevators.get(0));
        }

        Elevator onTheWay = elevators.stream()
                .filter(e -> e.getDirection() == externalRequest.getDirection())
                .filter(e -> isOnTheWay(e, externalRequest.getFloor()))
                .min(Comparator.comparingInt(e -> Math.abs(e.getCurrentFloor() - externalRequest.getFloor())))
                .orElse(null);

        if (onTheWay != null) {
            return onTheWay;
        }

        return elevators.stream()
                .min(Comparator.comparingInt(e -> Math.abs(e.getCurrentFloor() - externalRequest.getFloor())))
                .orElse(elevators.get(0));
    }

    private boolean isOnTheWay(Elevator elevator, int requestFloor) {
        return (elevator.getDirection() == Direction.UP && requestFloor >= elevator.getCurrentFloor())
                || (elevator.getDirection() == Direction.DOWN && requestFloor <= elevator.getCurrentFloor());
    }
}
