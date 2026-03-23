package org.example.Assignment4.parking_system.model;

public class Vehicle {
    private final String plate;
    private final VehicleType type;

    public Vehicle(String plate, VehicleType type) {
        if (plate == null || plate.isBlank()) {
            throw new IllegalArgumentException("plate must not be blank");
        }
        if (type == null) {
            throw new IllegalArgumentException("type must not be null");
        }
        this.plate = plate;
        this.type = type;
    }

    public VehicleType getType() {
        return type;
    }

    public String getPlate() {
        return plate;
    }
}

