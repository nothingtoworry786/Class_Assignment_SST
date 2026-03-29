package org.example.Assignment5.movie_booking_system.services;

import org.example.Assignment5.movie_booking_system.models.Booking;

public class EmailNotificationService implements NotificationService {
    @Override
    public void sendBookingConfirmation(Booking booking) {
        System.out.println("EMAIL: Booking confirmed for " + booking.getUser().getEmail() + " | BookingId=" + booking.getId());
    }

    @Override
    public void sendCancellation(Booking booking) {
        System.out.println("EMAIL: Booking cancelled for " + booking.getUser().getEmail() + " | BookingId=" + booking.getId());
    }
}
