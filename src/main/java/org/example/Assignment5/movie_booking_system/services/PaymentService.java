package org.example.Assignment5.movie_booking_system.services;

import org.example.Assignment5.movie_booking_system.enums.PaymentStatus;
import org.example.Assignment5.movie_booking_system.models.Payment;
import org.example.Assignment5.movie_booking_system.strategies.PaymentStrategy;
import org.example.Assignment5.movie_booking_system.utils.IdGenerator;

public class PaymentService {
    public Payment processPayment(String bookingId, double amount, PaymentStrategy paymentStrategy) {
        boolean success = paymentStrategy.pay(amount);
        PaymentStatus status = success ? PaymentStatus.COMPLETED : PaymentStatus.FAILED;
        return new Payment(IdGenerator.nextId("PAY"), bookingId, amount, paymentStrategy.mode(), status);
    }
}
