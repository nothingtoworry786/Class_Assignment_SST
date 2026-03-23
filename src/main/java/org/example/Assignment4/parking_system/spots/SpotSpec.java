package org.example.Assignment4.parking_system.spots;

import java.util.Arrays;

public class SpotSpec {
    private final SpotCategory category;
    private final double rate;
    private final int[] distances;

    public SpotSpec(SpotCategory category, double rate, int[] distances) {
        if (category == null) {
            throw new IllegalArgumentException("category must not be null");
        }
        if (distances == null || distances.length == 0) {
            throw new IllegalArgumentException("distances must not be empty");
        }
        if (rate < 0) {
            throw new IllegalArgumentException("rate must be >= 0");
        }
        this.category = category;
        this.rate = rate;
        this.distances = Arrays.copyOf(distances, distances.length);
    }

    public double getRatePerHour() {
        return rate;
    }

    public SpotCategory getCategory() {
        return category;
    }

    public int[] getDistances() {
        return Arrays.copyOf(distances, distances.length);
    }
}

