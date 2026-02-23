package org.example.Assignment1.Answers.Ex6;

public class EmailChannel implements NotificationChannel {

    @Override
    public void send(Notification n) {

        if (n.email == null) {
            throw new IllegalArgumentException("email required");
        }

        System.out.println(
                "EMAIL -> to=" + n.email +
                " subject=" + n.subject +
                " body=" + n.body
        );
    }

    @Override
    public String name() {
        return "email";
    }
}
