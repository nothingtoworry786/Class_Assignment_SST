package org.example.Assignment1.Answers.Ex4;

public class TripleRoom implements PricingComponent {
    public Money monthlyFee() {
        return new Money(12000.0);
    }
}
