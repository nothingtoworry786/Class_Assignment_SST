package org.example.Assignment1.Answers.Ex6;

public class NotificationSender {

    private final NotificationChannel channel;
    private final AuditLog audit;

    public NotificationSender(NotificationChannel channel, AuditLog audit) {
        this.channel = channel;
        this.audit = audit;
    }

    public void send(Notification n) {

        if (n == null) {
            throw new IllegalArgumentException("notification cannot be null");
        }

        channel.send(n);
        audit.add(channel.name() + " sent");
    }
}
