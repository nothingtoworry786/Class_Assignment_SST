package org.example.Assignment5.elevator;

import org.example.Assignment5.elevator.enums.Direction;
import org.example.Assignment5.elevator.models.Elevator;
import org.example.Assignment5.elevator.models.Floor;
import org.example.Assignment5.elevator.models.Scheduler;
import org.example.Assignment5.elevator.requests.ExternalRequest;

import java.util.HashMap;
import java.util.Map;

public class ElevatorSystem {
    private final Scheduler scheduler;
    private final Map<Integer, Floor> floors = new HashMap<>();

    public ElevatorSystem(Scheduler scheduler, int totalFloors) {
        this.scheduler = scheduler;
        for (int i = 0; i < totalFloors; i++) {
            floors.put(i, new Floor(i));
        }
    }

    public Elevator requestElevator(int floor, Direction direction) {
        ExternalRequest request = new ExternalRequest(floor, direction);
        floors.computeIfAbsent(floor, Floor::new).addRequest(request);
        return scheduler.selectElevator(request);
    }

    public Scheduler getScheduler() {
        return scheduler;
    }
}
