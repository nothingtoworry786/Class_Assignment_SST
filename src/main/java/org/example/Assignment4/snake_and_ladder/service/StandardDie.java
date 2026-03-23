package org.example.Assignment4.snake_and_ladder.service;

import java.util.Random;

public class StandardDie implements Die {
    private final int sides;
    private final Random rng = new Random();

    public StandardDie(int sides) {
        if (sides < 2) {
            throw new IllegalArgumentException("sides must be >= 2");
        }
        this.sides = sides;
    }

    @Override
    public int roll() {
        return rng.nextInt(sides) + 1;
    }
}

