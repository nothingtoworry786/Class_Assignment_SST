package org.example.Assignment1.Answers.Ex4;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Hostel Fee Calculator ===");

        BookingRequest req =
                new BookingRequest(
                        LegacyRoomTypes.DOUBLE,
                        List.of(AddOn.LAUNDRY, AddOn.MESS)
                );

        HostelFeeCalculator calc =
                new HostelFeeCalculator(
                        new FakeBookingRepo(),
                        new RoomRegistry(),
                        new AddOnRegistry()
                );

        calc.process(req);
    }
}
