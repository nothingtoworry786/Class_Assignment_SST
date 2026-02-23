package org.example.Assignment1.Answers.Ex4;

import java.util.*;

public class HostelFeeCalculator {

    private final FakeBookingRepo repo;
    private final RoomRegistry roomRegistry;
    private final AddOnRegistry addOnRegistry;

    public HostelFeeCalculator(FakeBookingRepo repo,
                               RoomRegistry roomRegistry,
                               AddOnRegistry addOnRegistry) {
        this.repo = repo;
        this.roomRegistry = roomRegistry;
        this.addOnRegistry = addOnRegistry;
    }

    public void process(BookingRequest req) {

        Money monthly = new Money(0);

        // Room contribution
        PricingComponent room =
                roomRegistry.get(req.roomType);

        monthly = monthly.plus(room.monthlyFee());

        // Add-ons contribution
        for (AddOn a : req.addOns) {
            PricingComponent addOn =
                    addOnRegistry.get(a);
            monthly = monthly.plus(addOn.monthlyFee());
        }

        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }
}
