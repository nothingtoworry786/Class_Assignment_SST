package org.example.Assignment1.Answers.Ex2;

public interface DiscountPolicy {
    double discount(String customerType,
                    double subtotal,
                    int distinctLines);
}
