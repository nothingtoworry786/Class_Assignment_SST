package org.example.Assignment5.elevator;

import org.example.Assignment5.elevator.enums.Direction;
import org.example.Assignment5.elevator.models.Elevator;
import org.example.Assignment5.elevator.models.Scheduler;
import org.example.Assignment5.elevator.requests.InternalRequest;
import org.example.Assignment5.elevator.strategies.NearestCarStrategy;
import org.example.Assignment5.elevator.strategies.SCANStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Elevator> elevators = new ArrayList<>();
        elevators.add(new Elevator(1, 0));
        elevators.add(new Elevator(2, 5));
        elevators.add(new Elevator(3, 9));

        Scheduler scheduler = new Scheduler(elevators, new NearestCarStrategy());
        ElevatorSystem system = new ElevatorSystem(scheduler, 12);

        Elevator e1 = system.requestElevator(3, Direction.UP);
        System.out.println("Assigned elevator for floor 3 UP: E" + e1.getId());
        e1.move();

        Elevator e2 = system.requestElevator(10, Direction.DOWN);
        System.out.println("Assigned elevator for floor 10 DOWN: E" + e2.getId());
        e2.move();

        e1.addRequest(new InternalRequest(11, e1.getId()));
        e1.move();
        System.out.println("E" + e1.getId() + " is now at floor " + e1.getCurrentFloor());

        scheduler.setStrategy(new SCANStrategy());
        Elevator e3 = system.requestElevator(6, Direction.DOWN);
        System.out.println("(SCAN) Assigned elevator for floor 6 DOWN: E" + e3.getId());
        e3.move();
    }
}
