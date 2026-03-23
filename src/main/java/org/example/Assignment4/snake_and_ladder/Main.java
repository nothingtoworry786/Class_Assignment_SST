package org.example.Assignment4.snake_and_ladder;

import org.example.Assignment4.snake_and_ladder.controller.SessionBuilder;
import org.example.Assignment4.snake_and_ladder.model.GameSession;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int gridSize = args.length > 0 ? Integer.parseInt(args[0]) : 30;
        String namesArg = args.length > 1 ? args[1] : "Alice,Bob,Charlie";
        String mode = args.length > 2 ? args[2] : "lenient";

        List<String> names = Arrays.asList(namesArg.split(","));

        GameSession session = new SessionBuilder().createGame(gridSize, names, mode);
        while (!session.hasEnded()) {
            session.playTurn();
        }

        System.out.println("Podium:");
        session.getPodium().forEach(c -> System.out.println(" - " + c.getName()));
    }
}

