package org.example.Assignment4.snake_and_ladder.model;

import org.example.Assignment4.snake_and_ladder.service.Die;

public interface Contestant {
    String getName();

    int getPosition();

    void setPosition(int position);

    int throwDie(Die die);
}

