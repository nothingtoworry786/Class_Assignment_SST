package org.example.Assignment3.Answers.adapter_payments.payments;

public interface PaymentGateway {
    String charge(String customerId, int amountCents);
}
