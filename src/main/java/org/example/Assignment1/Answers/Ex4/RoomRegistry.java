package org.example.Assignment1.Answers.Ex4;

import java.util.*;

public class RoomRegistry {

    private final Map<Integer, PricingComponent> rooms = new HashMap<>();

    public RoomRegistry() {
        rooms.put(LegacyRoomTypes.SINGLE, new SingleRoom());
        rooms.put(LegacyRoomTypes.DOUBLE, new DoubleRoom());
        rooms.put(LegacyRoomTypes.TRIPLE, new TripleRoom());
        rooms.put(LegacyRoomTypes.DELUXE, new DeluxeRoom());
    }

    public PricingComponent get(int roomType) {
        return rooms.get(roomType);
    }
}
