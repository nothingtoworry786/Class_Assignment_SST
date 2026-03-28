package org.example.Assignment5.elevator.requests;

public class InternalRequest extends Request {
    private final int elevatorId;

    public InternalRequest(int floor, int elevatorId) {
        super(floor);
        this.elevatorId = elevatorId;
    }

    public int getElevatorId() {
        return elevatorId;
    }
}
