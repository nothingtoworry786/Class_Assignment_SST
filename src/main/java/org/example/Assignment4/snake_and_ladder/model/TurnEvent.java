package org.example.Assignment4.snake_and_ladder.model;

public class TurnEvent {
    private final TurnPhase phase;
    private final Contestant contestant;
    private final int diceOutcome;
    private final Tile originTile;
    private final Tile destinationTile;
    private final Tile tile;

    public TurnEvent(
            TurnPhase phase,
            Contestant contestant,
            int diceOutcome,
            Tile originTile,
            Tile destinationTile,
            Tile tile
    ) {
        this.phase = phase;
        this.contestant = contestant;
        this.diceOutcome = diceOutcome;
        this.originTile = originTile;
        this.destinationTile = destinationTile;
        this.tile = tile;
    }

    public TurnPhase getPhase() {
        return phase;
    }

    public Contestant getContestant() {
        return contestant;
    }

    public int getDiceOutcome() {
        return diceOutcome;
    }

    public Tile getOriginTile() {
        return originTile;
    }

    public Tile getDestinationTile() {
        return destinationTile;
    }

    public Tile getTile() {
        return tile;
    }
}

