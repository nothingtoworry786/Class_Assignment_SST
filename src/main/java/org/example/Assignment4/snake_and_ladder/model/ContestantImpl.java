package org.example.Assignment4.snake_and_ladder.model;

import org.example.Assignment4.snake_and_ladder.service.Die;

public class ContestantImpl implements Contestant {
    private final String name;
    private int position;

    public ContestantImpl(String name, int initialPosition) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        this.name = name;
        this.position = initialPosition;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public int throwDie(Die die) {
        if (die == null) throw new IllegalArgumentException("die must not be null");
        return die.roll();
    }
}

