package org.example.Assignment4.snake_and_ladder.strategy;

import org.example.Assignment4.snake_and_ladder.model.SessionContext;
import org.example.Assignment4.snake_and_ladder.model.TurnEvent;
import org.example.Assignment4.snake_and_ladder.model.TurnPhase;

public class LenientMoveRule implements GameRule {
    @Override
    public void handle(TurnEvent event, SessionContext ctx) {
        if (event == null || ctx == null) return;
        if (event.getPhase() != TurnPhase.DICE_ROLLED) return;

        int origin = event.getContestant().getPosition();
        int destCandidate = origin + event.getDiceOutcome();
        int total = ctx.getBoard().getTotalSquares();

        int dest = Math.min(destCandidate, total);
        event.getContestant().setPosition(dest);
    }
}

