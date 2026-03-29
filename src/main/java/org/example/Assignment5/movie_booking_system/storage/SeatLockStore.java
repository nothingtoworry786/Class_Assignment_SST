package org.example.Assignment5.movie_booking_system.storage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.example.Assignment5.movie_booking_system.models.SeatLock;

public class SeatLockStore {
    private final Map<String, SeatLock> lockBySeat = new HashMap<>();

    public Optional<SeatLock> getBySeat(String showId, String showSeatId) {
        return Optional.ofNullable(lockBySeat.get(key(showId, showSeatId)));
    }

    public void add(SeatLock lock) {
        lockBySeat.put(key(lock.getShowId(), lock.getShowSeatId()), lock);
    }

    public void remove(String showId, String showSeatId) {
        lockBySeat.remove(key(showId, showSeatId));
    }

    public Collection<SeatLock> getAll() {
        return lockBySeat.values();
    }

    private String key(String showId, String showSeatId) {
        return showId + "::" + showSeatId;
    }
}
