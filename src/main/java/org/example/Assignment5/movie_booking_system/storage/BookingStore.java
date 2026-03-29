package org.example.Assignment5.movie_booking_system.storage;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.example.Assignment5.movie_booking_system.models.Booking;

public class BookingStore {
    private final Map<String, Booking> bookingsById = new HashMap<>();

    public void add(Booking booking) {
        bookingsById.put(booking.getId(), booking);
    }

    public Optional<Booking> getById(String bookingId) {
        return Optional.ofNullable(bookingsById.get(bookingId));
    }

    public void update(Booking booking) {
        bookingsById.put(booking.getId(), booking);
    }
}
