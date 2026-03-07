package org.example.Assignment3.Questions.adapter_payments.payments;

public class SafeCashClient {
    public SafeCashPayment createPayment(int amount, String user) {
        return new SafeCashPayment(amount, user);
    }
}
