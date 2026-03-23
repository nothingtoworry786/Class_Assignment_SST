package org.example.Assignment4.snake_and_ladder.strategy;

import org.example.Assignment4.snake_and_ladder.model.SessionContext;
import org.example.Assignment4.snake_and_ladder.model.Tile;
import org.example.Assignment4.snake_and_ladder.model.TurnEvent;
import org.example.Assignment4.snake_and_ladder.model.TurnPhase;

public class TileActionRule implements GameRule {
    @Override
    public void handle(TurnEvent event, SessionContext ctx) {
        if (event == null || ctx == null) return;
        if (event.getPhase() != TurnPhase.TILE_ENTERED) return;

        Tile tile = event.getTile();
        if (tile == null) return;
        tile.trigger(ctx);
    }
}

