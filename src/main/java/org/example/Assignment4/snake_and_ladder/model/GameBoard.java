package org.example.Assignment4.snake_and_ladder.model;

import java.util.HashMap;
import java.util.Map;

public class GameBoard {
    private final int totalSquares;
    private final Map<Integer, Tile> tilesByIndex = new HashMap<>();

    public GameBoard(int totalSquares) {
        if (totalSquares <= 1) {
            throw new IllegalArgumentException("totalSquares must be > 1");
        }
        this.totalSquares = totalSquares;
    }

    public void addTile(Tile tile) {
        if (tile == null) return;
        tilesByIndex.put(tile.getIndex(), tile);
    }

    public Tile getTile(int position) {
        return tilesByIndex.get(position);
    }

    public int getTotalSquares() {
        return totalSquares;
    }
}

