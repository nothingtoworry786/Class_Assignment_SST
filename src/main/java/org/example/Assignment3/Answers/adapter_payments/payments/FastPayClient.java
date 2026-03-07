package org.example.Assignment3.Answers.adapter_payments.payments;

public class FastPayClient {
    public String payNow(String custId, int amountCents) {
        return "FP#" + custId + ":" + amountCents;
    }
}
