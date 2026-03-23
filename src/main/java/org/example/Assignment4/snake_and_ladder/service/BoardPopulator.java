package org.example.Assignment4.snake_and_ladder.service;

import org.example.Assignment4.snake_and_ladder.model.Tile;
import org.example.Assignment4.snake_and_ladder.model.TileAction;
import org.example.Assignment4.snake_and_ladder.spots.ClimbUp;
import org.example.Assignment4.snake_and_ladder.spots.SlideDown;

import java.util.List;
import java.util.Random;

public class BoardPopulator {
    private final Random rng = new Random();

    public void populate(List<Tile> tiles, int totalSquares, int countPerType) {
        if (tiles == null) throw new IllegalArgumentException("tiles must not be null");
        if (totalSquares <= 1) throw new IllegalArgumentException("totalSquares must be > 1");
        if (countPerType < 0) throw new IllegalArgumentException("countPerType must be >= 0");

        // We replace the actions of specific tiles in-place.
        // Avoid start (1) and end (totalSquares).
        int maxStartIndex = totalSquares - 1;
        int minStartIndex = 2;
        if (minStartIndex > maxStartIndex) return;

        for (int i = 0; i < countPerType; i++) {
            int start = randomBetween(minStartIndex, maxStartIndex);
            int end = randomBetween(2, totalSquares - 1);

            if (end < start) {
                TileAction action = new SlideDown(start, end);
                setAction(tiles, start, action);
            } else if (end > start) {
                TileAction action = new ClimbUp(end, start);
                setAction(tiles, start, action);
            }
        }
    }

    private int randomBetween(int loInclusive, int hiInclusive) {
        return rng.nextInt(hiInclusive - loInclusive + 1) + loInclusive;
    }

    private void setAction(List<Tile> tiles, int index, TileAction action) {
        if (index <= 0 || index > tiles.size()) return;
        Tile existing = tiles.get(index - 1);
        if (existing != null) {
            existing.setAction(action);
        }
    }
}

