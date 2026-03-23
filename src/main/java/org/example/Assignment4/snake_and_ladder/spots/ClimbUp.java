package org.example.Assignment4.snake_and_ladder.spots;

import org.example.Assignment4.snake_and_ladder.model.SessionContext;
import org.example.Assignment4.snake_and_ladder.model.TileAction;

public class ClimbUp implements TileAction {
    private final int bottom;
    private final int top;

    public ClimbUp(int bottom, int top) {
        if (bottom <= 1) throw new IllegalArgumentException("bottom must be > 1");
        this.bottom = bottom;
        this.top = top;
    }

    @Override
    public void apply(SessionContext ctx) {
        if (ctx == null) return;
        int current = ctx.getCurrentContestant().getPosition();
        if (current != bottom) return;
        if (top >= 1 && top <= ctx.getBoard().getTotalSquares()) {
            ctx.getCurrentContestant().setPosition(top);
        }
    }
}

