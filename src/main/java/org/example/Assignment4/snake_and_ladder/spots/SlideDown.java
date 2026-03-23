package org.example.Assignment4.snake_and_ladder.spots;

import org.example.Assignment4.snake_and_ladder.model.SessionContext;
import org.example.Assignment4.snake_and_ladder.model.TileAction;

public class SlideDown implements TileAction {
    private final int mouth;
    private final int tail;

    public SlideDown(int mouth, int tail) {
        if (mouth <= 1) throw new IllegalArgumentException("mouth must be > 1");
        this.mouth = mouth;
        this.tail = tail;
    }

    @Override
    public void apply(SessionContext ctx) {
        // Move contestant to tail if within bounds.
        if (ctx == null) return;
        int current = ctx.getCurrentContestant().getPosition();
        if (current != mouth) return;
        if (tail >= 1 && tail <= ctx.getBoard().getTotalSquares()) {
            ctx.getCurrentContestant().setPosition(tail);
        }
    }
}

