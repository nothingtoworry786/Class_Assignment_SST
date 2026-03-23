package org.example.Assignment4.snake_and_ladder.model;

import org.example.Assignment4.snake_and_ladder.service.Die;
import org.example.Assignment4.snake_and_ladder.service.EventProcessor;

import java.time.LocalDateTime;

public class GameSession {
    private final SessionContext ctx;
    private final EventProcessor processor;
    private final Die die;

    public GameSession(SessionContext ctx, EventProcessor processor, Die die) {
        if (ctx == null) throw new IllegalArgumentException("ctx must not be null");
        if (processor == null) throw new IllegalArgumentException("processor must not be null");
        if (die == null) throw new IllegalArgumentException("die must not be null");
        this.ctx = ctx;
        this.processor = processor;
        this.die = die;
    }

    public void playTurn() {
        if (hasEnded()) return;

        Contestant contestant = ctx.getCurrentContestant();
        if (contestant == null) return;

        int origin = contestant.getPosition();
        Tile originTile = ctx.getBoard().getTile(origin);

        int roll = contestant.throwDie(die);
        ctx.recordRoll(roll);

        int destinationCandidate = origin + roll;
        Tile destinationTile = ctx.getBoard().getTile(destinationCandidate);

        TurnEvent diceEvent = new TurnEvent(TurnPhase.DICE_ROLLED, contestant, roll, originTile, destinationTile, null);
        processor.dispatch(diceEvent, ctx);

        // After movement rules, contestant has been moved (or not). Trigger tile action if any.
        int newPos = contestant.getPosition();
        Tile tileEntered = ctx.getBoard().getTile(newPos);

        TurnEvent enterEvent = new TurnEvent(
                TurnPhase.TILE_ENTERED,
                contestant,
                roll,
                originTile,
                tileEntered,
                tileEntered
        );
        processor.dispatch(enterEvent, ctx);

        // If landed on final square, mark as won (idempotent).
        if (newPos == ctx.getBoard().getTotalSquares()) {
            ctx.markAsWon(roll);
        }

        ctx.advanceTurn();
    }

    public boolean hasEnded() {
        return ctx.remainingContestants() <= 0 || ctx.getPodium().size() >= 3;
    }

    public java.util.List<Contestant> getPodium() {
        return ctx.getPodium();
    }
}

