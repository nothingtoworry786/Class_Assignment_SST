package org.example.Assignment5.elevator.models;

import org.example.Assignment5.elevator.enums.Direction;
import org.example.Assignment5.elevator.enums.DoorState;
import org.example.Assignment5.elevator.enums.ElevatorState;
import org.example.Assignment5.elevator.requests.Request;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Elevator {
    private final int id;
    private int currentFloor;
    private Direction direction;
    private ElevatorState state;
    private DoorState doorState;
    private final Queue<Request> requests;

    public Elevator(int id, int currentFloor) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.direction = Direction.UP;
        this.state = ElevatorState.IDLE;
        this.doorState = DoorState.CLOSED;
        this.requests = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public ElevatorState getState() {
        return state;
    }

    public DoorState getDoorState() {
        return doorState;
    }

    public void addRequest(Request request) {
        requests.offer(Objects.requireNonNull(request));
        if (state == ElevatorState.IDLE) {
            state = ElevatorState.STOPPED;
        }
    }

    public void move() {
        if (requests.isEmpty()) {
            state = ElevatorState.IDLE;
            return;
        }

        Request nextRequest = requests.poll();
        int targetFloor = nextRequest.getFloor();

        if (targetFloor == currentFloor) {
            stop();
            openDoor();
            closeDoor();
            state = requests.isEmpty() ? ElevatorState.IDLE : ElevatorState.STOPPED;
            return;
        }

        state = ElevatorState.MOVING;
        direction = targetFloor > currentFloor ? Direction.UP : Direction.DOWN;

        while (currentFloor != targetFloor) {
            currentFloor += (direction == Direction.UP ? 1 : -1);
        }

        stop();
        openDoor();
        closeDoor();

        state = requests.isEmpty() ? ElevatorState.IDLE : ElevatorState.STOPPED;
    }

    public void stop() {
        state = ElevatorState.STOPPED;
    }

    public void openDoor() {
        doorState = DoorState.OPENING;
        doorState = DoorState.OPEN;
    }

    public void closeDoor() {
        doorState = DoorState.CLOSING;
        doorState = DoorState.CLOSED;
    }
}
