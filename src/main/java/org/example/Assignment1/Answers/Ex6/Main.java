package org.example.Assignment1.Answers.Ex6;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Notification Demo ===");

        AuditLog audit = new AuditLog();

        Notification n =
                new Notification(
                        "Welcome",
                        "Hello and welcome to SST!",
                        "riya@sst.edu",
                        "+1 9876543210"
                );

        NotificationSender email =
                new NotificationSender(new EmailChannel(), audit);

        NotificationSender sms =
                new NotificationSender(new SmsChannel(), audit);

        NotificationSender wa =
                new NotificationSender(new WhatsAppChannel(), audit);

        email.send(n);
        sms.send(n);

        try {
            wa.send(n);
        } catch (RuntimeException ex) {
            System.out.println("WA ERROR: " + ex.getMessage());
            audit.add("WA failed");
        }

        System.out.println("AUDIT entries=" + audit.size());
    }
}
