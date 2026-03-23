package org.example.Assignment4.parking_system.strategy;

import org.example.Assignment4.parking_system.model.VehicleType;
import org.example.Assignment4.parking_system.spots.SpotCategory;

import java.util.List;

public interface SlotDecisionStrategy {
    SpotCategory minSlotFor(VehicleType vehicleType);

    // Returns categories to try, in ascending order, including any "upgraded" minimum.
    List<SpotCategory> categoriesToTry(VehicleType vehicleType, SpotCategory requestedSlot);
}

