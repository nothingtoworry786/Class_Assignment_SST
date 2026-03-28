package org.example.Assignment5.elevator.requests;

import org.example.Assignment5.elevator.enums.Direction;

public class ExternalRequest extends Request {
    private final Direction direction;

    public ExternalRequest(int floor, Direction direction) {
        super(floor);
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
}
