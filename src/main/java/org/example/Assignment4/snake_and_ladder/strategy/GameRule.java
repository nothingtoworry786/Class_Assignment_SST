package org.example.Assignment4.snake_and_ladder.strategy;

import org.example.Assignment4.snake_and_ladder.model.SessionContext;
import org.example.Assignment4.snake_and_ladder.model.TurnEvent;

public interface GameRule {
    void handle(TurnEvent event, SessionContext ctx);
}

