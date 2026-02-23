package org.example.Assignment1.Answers.Ex6;

public class WhatsAppChannel implements NotificationChannel {

    @Override
    public void send(Notification n) {

        if (n.phone == null || !n.phone.startsWith("+")) {
            throw new IllegalArgumentException(
                    "phone must start with + and country code"
            );
        }

        System.out.println(
                "WA -> to=" + n.phone +
                " body=" + n.body
        );
    }

    @Override
    public String name() {
        return "wa";
    }
}
