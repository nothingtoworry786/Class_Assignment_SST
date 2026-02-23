package org.example.Assignment1.Answers.Ex4;

import java.util.*;

public class BookingRequest {

    public final int roomType;
    public final List<AddOn> addOns;

    public BookingRequest(int roomType, List<AddOn> addOns) {
        this.roomType = roomType;
        this.addOns = addOns;
    }
}
