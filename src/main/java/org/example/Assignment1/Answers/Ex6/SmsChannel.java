package org.example.Assignment1.Answers.Ex6;

public class SmsChannel implements NotificationChannel {

    @Override
    public void send(Notification n) {

        if (n.phone == null) {
            throw new IllegalArgumentException("phone required");
        }

        System.out.println(
                "SMS -> to=" + n.phone +
                " body=" + n.body
        );
    }

    @Override
    public String name() {
        return "sms";
    }
}
