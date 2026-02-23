package org.example.Assignment1.Answers.Ex4;

import java.util.*;

public class AddOnRegistry {

    private final Map<AddOn, PricingComponent> addOns = new HashMap<>();

    public AddOnRegistry() {
        addOns.put(AddOn.MESS, new MessAddOn());
        addOns.put(AddOn.LAUNDRY, new LaundryAddOn());
        addOns.put(AddOn.GYM, new GymAddOn());
    }

    public PricingComponent get(AddOn addOn) {
        return addOns.get(addOn);
    }
}
