package org.example.Assignment4.snake_and_ladder.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SessionContext {
    private final GameBoard board;
    private final Queue<Contestant> activeContestants;
    private Contestant currentContestant;
    private final List<Integer> rollHistory = new ArrayList<>();
    private boolean reRollScheduled;
    private boolean wonFlag;
    private final List<Contestant> podium = new ArrayList<>();

    public SessionContext(GameBoard board, List<? extends Contestant> contestants) {
        if (board == null) throw new IllegalArgumentException("board must not be null");
        if (contestants == null || contestants.isEmpty()) throw new IllegalArgumentException("contestants must not be empty");
        this.board = board;
        this.activeContestants = new ConcurrentLinkedQueue<>();
        this.activeContestants.addAll(contestants);
        this.currentContestant = this.activeContestants.peek();
    }

    public void advanceTurn() {
        if (wonFlag) {
            wonFlag = false;
            // Remove winner from active pool.
            Contestant cur = activeContestants.poll();
            if (cur != null) {
                // If this was the last remaining contestant, we keep podium logic consistent.
            }

            if (activeContestants.size() == 1) {
                addLastContestantToPodium();
            }
            currentContestant = activeContestants.peek();
            reRollScheduled = false;
            return;
        }

        if (reRollScheduled) {
            reRollScheduled = false; // same contestant continues.
            return;
        }

        // Rotate: move current to the back.
        Contestant cur = activeContestants.poll();
        if (cur != null) {
            activeContestants.add(cur);
        }
        currentContestant = activeContestants.peek();
    }

    public void markAsWon(int value) {
        // Idempotent: don't re-add to podium.
        if (wonFlag) return;
        wonFlag = true;
        if (currentContestant != null && !podium.contains(currentContestant)) {
            podium.add(currentContestant);
        }
    }

    public void recordRoll(int value) {
        rollHistory.add(value);
    }

    public void scheduleReRoll() {
        reRollScheduled = true;
    }

    public void addLastContestantToPodium() {
        Contestant last = activeContestants.peek();
        if (last != null && !podium.contains(last)) {
            podium.add(last);
        }
        activeContestants.clear();
        currentContestant = null;
    }

    public List<Integer> getRollHistory() {
        return new ArrayList<>(rollHistory);
    }

    public GameBoard getBoard() {
        return board;
    }

    public Contestant getCurrentContestant() {
        return currentContestant;
    }

    public int remainingContestants() {
        return activeContestants.size();
    }

    public List<Contestant> getPodium() {
        return new ArrayList<>(podium);
    }
}

