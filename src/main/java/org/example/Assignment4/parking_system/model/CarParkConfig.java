package org.example.Assignment4.parking_system.model;

import org.example.Assignment4.parking_system.spots.SpotSpec;

import java.util.ArrayList;
import java.util.List;

public class CarParkConfig {
    private int totalFloors;
    private int[] entrancesPerFloor;
    private final List<SpotSpec> floorSpecs = new ArrayList<>();

    public CarParkConfig setTotalFloors(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("totalFloors must be > 0");
        }
        this.totalFloors = n;
        return this;
    }

    public CarParkConfig setEntrancesPerFloor(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("entrancesPerFloor must not be empty");
        }
        this.entrancesPerFloor = arr.clone();
        return this;
    }

    public void addFloorSpecs(List<SpotSpec> specs) {
        if (specs == null || specs.isEmpty()) {
            throw new IllegalArgumentException("specs must not be empty");
        }
        floorSpecs.addAll(specs);
    }

    public int getTotalFloors() {
        return totalFloors;
    }

    public int[] getEntrancesPerFloor() {
        return entrancesPerFloor == null ? new int[0] : entrancesPerFloor.clone();
    }

    public List<SpotSpec> getFloorSpecs() {
        return new ArrayList<>(floorSpecs);
    }
}

