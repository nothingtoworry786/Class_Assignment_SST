package org.example.Assignment5.movie_booking_system.models;

import java.time.LocalDateTime;
import java.util.List;

import org.example.Assignment5.movie_booking_system.enums.BookingStatus;

public class Booking {
    private final String id;
    private final User user;
    private final Show show;
    private final List<ShowSeat> seats;
    private final LocalDateTime createdAt;
    private final double totalAmount;
    private BookingStatus status;

    public Booking(String id, User user, Show show, List<ShowSeat> seats, double totalAmount) {
        this.id = id;
        this.user = user;
        this.show = show;
        this.seats = seats;
        this.createdAt = LocalDateTime.now();
        this.totalAmount = totalAmount;
        this.status = BookingStatus.PENDING;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Show getShow() {
        return show;
    }

    public List<ShowSeat> getSeats() {
        return seats;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}
