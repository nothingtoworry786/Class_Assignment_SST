package org.example.Assignment4.parking_system.service;

import org.example.Assignment4.parking_system.model.CarParkConfig;
import org.example.Assignment4.parking_system.model.ParkReceipt;
import org.example.Assignment4.parking_system.model.Vehicle;
import org.example.Assignment4.parking_system.model.VehicleType;
import org.example.Assignment4.parking_system.spots.SpotCategory;
import org.example.Assignment4.parking_system.spots.SpotSpec;
import org.example.Assignment4.parking_system.strategy.SlotDecisionStrategy;
import org.example.Assignment4.parking_system.strategy.DefaultSlotDecisionStrategy;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

public class CarPark {
    private static class Spot {
        private final String id;
        private final int distance;

        private Spot(String id, int distance) {
            this.id = id;
            this.distance = distance;
        }
    }

    private final Map<SpotCategory, SpotSpec> specByCategory;
    private final Map<SpotCategory, Queue<Spot>> availableSpots;
    private final SlotDecisionStrategy slotDecisionStrategy;

    public CarPark(CarParkConfig config, SlotDecisionStrategy slotDecisionStrategy) {
        Objects.requireNonNull(config, "config");
        this.slotDecisionStrategy = slotDecisionStrategy == null ? new DefaultSlotDecisionStrategy() : slotDecisionStrategy;

        List<SpotSpec> specs = config.getFloorSpecs();
        if (specs.isEmpty()) {
            throw new IllegalArgumentException("No SpotSpec provided");
        }

        Map<SpotCategory, SpotSpec> tmpSpecs = new EnumMap<>(SpotCategory.class);
        for (SpotSpec s : specs) {
            tmpSpecs.put(s.getCategory(), s);
        }
        this.specByCategory = Collections.unmodifiableMap(tmpSpecs);
        this.availableSpots = new EnumMap<>(SpotCategory.class);

        int floors = config.getTotalFloors();
        for (Map.Entry<SpotCategory, SpotSpec> e : specByCategory.entrySet()) {
            SpotSpec spec = e.getValue();
            Queue<Spot> q = new ArrayDeque<>();

            int idx = 0;
            for (int floor = 1; floor <= floors; floor++) {
                for (int distance : spec.getDistances()) {
                    String spotId = e.getKey().name() + "-F" + floor + "-S" + (idx++);
                    q.add(new Spot(spotId, distance));
                }
            }
            availableSpots.put(e.getKey(), q);
        }
    }

    public synchronized Map<SpotCategory, Long> status() {
        Map<SpotCategory, Long> counts = new EnumMap<>(SpotCategory.class);
        for (SpotCategory c : SpotCategory.values()) {
            Queue<Spot> q = availableSpots.get(c);
            counts.put(c, q == null ? 0L : (long) q.size());
        }
        return counts;
    }

    public synchronized ParkReceipt park(Vehicle vehicle, LocalDateTime entryTime, SpotCategory requestedSlot, int entranceId) {
        Objects.requireNonNull(vehicle, "vehicle");
        Objects.requireNonNull(entryTime, "entryTime");

        List<SpotCategory> tryOrder = slotDecisionStrategy.categoriesToTry(vehicle.getType(), requestedSlot);

        for (SpotCategory cat : tryOrder) {
            Queue<Spot> q = availableSpots.get(cat);
            if (q == null || q.isEmpty()) continue;

            Spot spot = q.poll();
            SpotSpec reserved = specByCategory.get(cat);
            if (reserved == null) {
                throw new IllegalStateException("Missing SpotSpec for category " + cat);
            }
            return new ParkReceipt(cat, spot.id, reserved, entryTime);
        }

        throw new IllegalStateException("No available slot for " + vehicle.getType());
    }

    public synchronized double exit(ParkReceipt receipt, LocalDateTime exitTime) {
        Objects.requireNonNull(receipt, "receipt");
        Objects.requireNonNull(exitTime, "exitTime");

        LocalDateTime entryTime = receipt.getEntryTime();
        if (exitTime.isBefore(entryTime)) {
            throw new IllegalArgumentException("exitTime must be >= entryTime");
        }

        double rate = receipt.getReservedSpot().getRatePerHour();
        long minutes = Duration.between(entryTime, exitTime).toMinutes();
        double hours = Math.ceil(minutes / 60.0);
        if (hours < 1.0) hours = 1.0; // minimum 1 hour billing for non-zero duration

        double cost = hours * rate;

        SpotCategory cat = receipt.getAllocatedSlotType();
        Queue<Spot> q = availableSpots.get(cat);
        if (q == null) {
            q = new ArrayDeque<>();
            availableSpots.put(cat, q);
        }
        q.add(new Spot(receipt.getAllocatedSlotId(), 0));
        return cost;
    }

    public SpotCategory minSlotFor(VehicleType vehicleType) {
        return slotDecisionStrategy.minSlotFor(vehicleType);
    }
}

