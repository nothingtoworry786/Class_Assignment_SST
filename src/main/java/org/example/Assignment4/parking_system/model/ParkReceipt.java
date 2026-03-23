package org.example.Assignment4.parking_system.model;

import org.example.Assignment4.parking_system.spots.SpotCategory;
import org.example.Assignment4.parking_system.spots.SpotSpec;

import java.time.LocalDateTime;

public class ParkReceipt {
    private final SpotCategory allocatedSlotType;
    private final String allocatedSlotId;
    private final SpotSpec reservedSpot;

    private final LocalDateTime entryTime;

    public ParkReceipt(SpotCategory allocatedSlotType, String allocatedSlotId, SpotSpec reservedSpot, LocalDateTime entryTime) {
        if (allocatedSlotType == null) {
            throw new IllegalArgumentException("allocatedSlotType must not be null");
        }
        if (allocatedSlotId == null || allocatedSlotId.isBlank()) {
            throw new IllegalArgumentException("allocatedSlotId must not be blank");
        }
        if (reservedSpot == null) {
            throw new IllegalArgumentException("reservedSpot must not be null");
        }
        if (entryTime == null) {
            throw new IllegalArgumentException("entryTime must not be null");
        }
        this.allocatedSlotType = allocatedSlotType;
        this.allocatedSlotId = allocatedSlotId;
        this.reservedSpot = reservedSpot;
        this.entryTime = entryTime;
    }

    public SpotCategory getAllocatedSlotType() {
        return allocatedSlotType;
    }

    public String getAllocatedSlotId() {
        return allocatedSlotId;
    }

    public SpotSpec getReservedSpot() {
        return reservedSpot;
    }

    // Used internally by CarPark.exit for cost calculation.
    public LocalDateTime getEntryTime() {
        return entryTime;
    }
}

