package org.example.Assignment1.Answers.Ex4;

public class FakeBookingRepo {

    public void save(String id,
                     BookingRequest req,
                     Money monthly,
                     Money deposit) {

        System.out.println("Saved booking: " + id);
    }
}
