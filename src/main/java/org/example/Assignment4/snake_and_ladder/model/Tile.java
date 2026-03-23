package org.example.Assignment4.snake_and_ladder.model;

public class Tile {
    private final int index;
    private TileAction action;

    public Tile(int index, TileAction action) {
        this.index = index;
        this.action = action;
    }

    public void setAction(TileAction action) {
        this.action = action;
    }

    public void trigger(SessionContext ctx) {
        if (action != null) {
            action.apply(ctx);
        }
    }

    public int getIndex() {
        return index;
    }

    public TileAction getAction() {
        return action;
    }
}

