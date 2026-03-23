package org.example.Assignment4.snake_and_ladder.controller;

import org.example.Assignment4.snake_and_ladder.model.ContestantImpl;
import org.example.Assignment4.snake_and_ladder.model.GameBoard;
import org.example.Assignment4.snake_and_ladder.model.GameSession;
import org.example.Assignment4.snake_and_ladder.model.SessionContext;
import org.example.Assignment4.snake_and_ladder.model.Tile;
import org.example.Assignment4.snake_and_ladder.model.TurnEvent;
import org.example.Assignment4.snake_and_ladder.model.TurnPhase;
import org.example.Assignment4.snake_and_ladder.service.BoardPopulator;
import org.example.Assignment4.snake_and_ladder.service.EventProcessor;
import org.example.Assignment4.snake_and_ladder.service.Die;
import org.example.Assignment4.snake_and_ladder.service.StandardDie;
import org.example.Assignment4.snake_and_ladder.assembler.RuleSetAssembler;

import java.util.ArrayList;
import java.util.List;

public class SessionBuilder {

    public GameSession createGame(int gridSize, List<String> names, String mode) {
        if (gridSize <= 1) {
            throw new IllegalArgumentException("gridSize must be > 1");
        }
        if (names == null || names.isEmpty()) {
            throw new IllegalArgumentException("names must not be empty");
        }

        // Treat gridSize as number of squares on the board.
        int totalSquares = gridSize;

        List<Tile> tiles = new ArrayList<>();
        GameBoard board = new GameBoard(totalSquares);

        // Create "empty" tiles for lookup.
        for (int i = 1; i <= totalSquares; i++) {
            tiles.add(new Tile(i, null));
        }
        for (Tile t : tiles) {
            board.addTile(t);
        }

        // Populate slide/climb actions.
        int countPerType = Math.max(1, totalSquares / 10);
        BoardPopulator populator = new BoardPopulator();
        populator.populate(tiles, totalSquares, countPerType);

        List<ContestantImpl> contestants = new ArrayList<>();
        for (String n : names) {
            contestants.add(new ContestantImpl(n, 1));
        }

        SessionContext ctx = new SessionContext(board, contestants);

        RuleSetAssembler assembler = new RuleSetAssembler();
        EventProcessor processor = assembler.build(mode);

        Die die = new StandardDie(6);

        return new GameSession(ctx, processor, die);
    }
}

