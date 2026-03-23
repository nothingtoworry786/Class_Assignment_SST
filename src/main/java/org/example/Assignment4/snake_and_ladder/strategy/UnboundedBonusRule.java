package org.example.Assignment4.snake_and_ladder.strategy;

import org.example.Assignment4.snake_and_ladder.model.SessionContext;
import org.example.Assignment4.snake_and_ladder.model.TurnEvent;
import org.example.Assignment4.snake_and_ladder.model.TurnPhase;

public class UnboundedBonusRule implements GameRule {
    @Override
    public void handle(TurnEvent event, SessionContext ctx) {
        if (event == null || ctx == null) return;
        if (event.getPhase() != TurnPhase.DICE_ROLLED) return;

        if (event.getDiceOutcome() == 6) {
            ctx.scheduleReRoll();
        }
    }
}

