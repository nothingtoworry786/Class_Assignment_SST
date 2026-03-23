package org.example.Assignment4.parking_system.strategy;

import org.example.Assignment4.parking_system.model.VehicleType;
import org.example.Assignment4.parking_system.spots.SpotCategory;

import java.util.ArrayList;
import java.util.List;

public class DefaultSlotDecisionStrategy implements SlotDecisionStrategy {
    @Override
    public SpotCategory minSlotFor(VehicleType vehicleType) {
        if (vehicleType == null) {
            throw new IllegalArgumentException("vehicleType must not be null");
        }
        return switch (vehicleType) {
            case BIKE -> SpotCategory.SMALL;
            case CAR -> SpotCategory.MEDIUM;
            case TRUCK -> SpotCategory.LARGE;
        };
    }

    @Override
    public List<SpotCategory> categoriesToTry(VehicleType vehicleType, SpotCategory requestedSlot) {
        SpotCategory min = minSlotFor(vehicleType);
        SpotCategory desired = requestedSlot == null ? min : requestedSlot;
        desired = ensureAtLeast(desired, min);

        List<SpotCategory> order = new ArrayList<>(3);
        for (SpotCategory c : SpotCategory.values()) {
            if (order(c) >= order(desired)) {
                order.add(c);
            }
        }
        return order;
    }

    private SpotCategory ensureAtLeast(SpotCategory desired, SpotCategory min) {
        return order(desired) >= order(min) ? desired : min;
    }

    private int order(SpotCategory c) {
        return switch (c) {
            case SMALL -> 0;
            case MEDIUM -> 1;
            case LARGE -> 2;
        };
    }
}

