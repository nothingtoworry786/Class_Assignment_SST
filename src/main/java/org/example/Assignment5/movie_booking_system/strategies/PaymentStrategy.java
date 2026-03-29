package org.example.Assignment5.movie_booking_system.strategies;

public interface PaymentStrategy {
    boolean pay(double amount);

    String mode();
}
