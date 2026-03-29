package org.example.Assignment5.movie_booking_system.models;

import org.example.Assignment5.movie_booking_system.enums.SeatStatus;

public class ShowSeat {
    private final String id;
    private final Seat seat;
    private final double price;
    private SeatStatus status;

    public ShowSeat(String id, Seat seat, double price) {
        this.id = id;
        this.seat = seat;
        this.price = price;
        this.status = SeatStatus.AVAILABLE;
    }

    public String getId() {
        return id;
    }

    public Seat getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }
}
