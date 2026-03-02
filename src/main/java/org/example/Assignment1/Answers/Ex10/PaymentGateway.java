package org.example.Assignment1.Answers.Ex10;

public class PaymentGateway implements PaymentService {

    @Override
    public String charge(String studentId, double amount) {
        return "TXN-9001";
    }
}
