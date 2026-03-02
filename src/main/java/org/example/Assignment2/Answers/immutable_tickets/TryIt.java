package org.example.Assignment2.Answers.immutable_tickets;

import org.example.Assignment2.Answers.immutable_tickets.tickets.IncidentTicket;
import org.example.Assignment2.Answers.immutable_tickets.tickets.TicketService;

import java.util.List;

public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket t1 = service.createTicket(
                "TCK-1001",
                "reporter@example.com",
                "Payment failing on checkout"
        );
        System.out.println("Created: " + t1);

        IncidentTicket t2 = service.assign(t1, "agent@example.com");
        IncidentTicket t3 = service.escalateToCritical(t2);

        System.out.println("\nAfter service updates (new instances): ");
        System.out.println("t1: " + t1);
        System.out.println("t2: " + t2);
        System.out.println("t3: " + t3);

        List<String> externalTags = t3.getTags();
        try {
            externalTags.add("HACKED_FROM_OUTSIDE");
        } catch (UnsupportedOperationException e) {
            System.out.println("\nTags are immutable from outside (as expected).");
        }

        System.out.println("\nFinal ticket: " + t3);
    }
}
