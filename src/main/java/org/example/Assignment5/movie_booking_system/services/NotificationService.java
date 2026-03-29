package org.example.Assignment5.movie_booking_system.services;

import org.example.Assignment5.movie_booking_system.models.Booking;

public interface NotificationService {
    void sendBookingConfirmation(Booking booking);

    void sendCancellation(Booking booking);
}
