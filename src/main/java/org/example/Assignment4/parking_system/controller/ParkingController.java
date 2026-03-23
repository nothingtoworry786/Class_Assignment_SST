package org.example.Assignment4.parking_system.controller;

import org.example.Assignment4.parking_system.model.CarParkConfig;
import org.example.Assignment4.parking_system.model.ParkReceipt;
import org.example.Assignment4.parking_system.model.Vehicle;
import org.example.Assignment4.parking_system.service.CarPark;
import org.example.Assignment4.parking_system.spots.SpotCategory;

import java.time.LocalDateTime;
import java.util.Map;

public class ParkingController {
    private final CarPark carPark;

    public ParkingController(CarPark carPark) {
        if (carPark == null) {
            throw new IllegalArgumentException("carPark must not be null");
        }
        this.carPark = carPark;
    }

    public Map<SpotCategory, Long> status() {
        return carPark.status();
    }

    public ParkReceipt park(Vehicle vehicle, LocalDateTime entryTime, SpotCategory requestedSlot, int entranceId) {
        return carPark.park(vehicle, entryTime, requestedSlot, entranceId);
    }

    public double exit(ParkReceipt receipt, LocalDateTime exitTime) {
        return carPark.exit(receipt, exitTime);
    }
}

