package org.example.Assignment1.Answers.Ex10;

public class SimplePricingService implements PricingService {

    @Override
    public double calculateFare(double km) {
        double fare = 50.0 + km * 6.6666666667;
        return Math.round(fare * 100.0) / 100.0;
    }
}
