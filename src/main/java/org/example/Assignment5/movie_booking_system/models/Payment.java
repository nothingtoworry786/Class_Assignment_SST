package org.example.Assignment5.movie_booking_system.models;

import java.time.LocalDateTime;

import org.example.Assignment5.movie_booking_system.enums.PaymentStatus;

public class Payment {
    private final String id;
    private final String bookingId;
    private final double amount;
    private final String mode;
    private final LocalDateTime paidAt;
    private PaymentStatus status;

    public Payment(String id, String bookingId, double amount, String mode, PaymentStatus status) {
        this.id = id;
        this.bookingId = bookingId;
        this.amount = amount;
        this.mode = mode;
        this.paidAt = LocalDateTime.now();
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getBookingId() {
        return bookingId;
    }

    public double getAmount() {
        return amount;
    }

    public String getMode() {
        return mode;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
