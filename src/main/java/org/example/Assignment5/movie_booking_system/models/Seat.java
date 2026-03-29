package org.example.Assignment5.movie_booking_system.models;

import org.example.Assignment5.movie_booking_system.enums.SeatType;

public class Seat {
    private final String id;
    private final int row;
    private final int number;
    private final SeatType seatType;
    private final double basePrice;

    public Seat(String id, int row, int number, SeatType seatType, double basePrice) {
        this.id = id;
        this.row = row;
        this.number = number;
        this.seatType = seatType;
        this.basePrice = basePrice;
    }

    public String getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public int getNumber() {
        return number;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public double getBasePrice() {
        return basePrice;
    }
}
