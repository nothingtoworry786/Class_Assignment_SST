package org.example.Assignment4.snake_and_ladder.strategy;

import org.example.Assignment4.snake_and_ladder.model.SessionContext;
import org.example.Assignment4.snake_and_ladder.model.TurnEvent;
import org.example.Assignment4.snake_and_ladder.model.TurnPhase;

import java.util.List;

public class CappedBonusRule implements GameRule {
    private static final int MAX_SIXES = 3;

    @Override
    public void handle(TurnEvent event, SessionContext ctx) {
        if (event == null || ctx == null) return;
        if (event.getPhase() != TurnPhase.DICE_ROLLED) return;

        if (event.getDiceOutcome() != 6) return;

        List<Integer> history = ctx.getRollHistory();
        int sixCount = 0;
        for (Integer v : history) {
            if (v != null && v == 6) sixCount++;
        }

        // Allow at most MAX_SIXES total sixes to grant re-roll.
        if (sixCount <= MAX_SIXES) {
            ctx.scheduleReRoll();
        }
    }
}

