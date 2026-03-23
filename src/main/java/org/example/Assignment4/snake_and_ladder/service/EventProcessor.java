package org.example.Assignment4.snake_and_ladder.service;

import org.example.Assignment4.snake_and_ladder.model.GameSession;
import org.example.Assignment4.snake_and_ladder.model.SessionContext;
import org.example.Assignment4.snake_and_ladder.model.TurnEvent;
import org.example.Assignment4.snake_and_ladder.strategy.GameRule;

import java.util.ArrayList;
import java.util.List;

public class EventProcessor {
    private final List<GameRule> registeredRules = new ArrayList<>();

    public void addRule(GameRule rule) {
        if (rule == null) throw new IllegalArgumentException("rule must not be null");
        registeredRules.add(rule);
    }

    public void dispatch(TurnEvent event, SessionContext ctx) {
        for (GameRule rule : registeredRules) {
            rule.handle(event, ctx);
        }
    }
}

