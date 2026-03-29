package org.example.Assignment5.movie_booking_system.strategies;

public class CardPayment implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        return amount > 0;
    }

    @Override
    public String mode() {
        return "CARD";
    }
}
