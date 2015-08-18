package com.randspy.tictactoe;

import java.util.Arrays;
import java.util.List;

public class Players {
    private static final int STARTING_INDEX = 0;

    private List<Player> players;
    private int currentPlayerIndex = STARTING_INDEX;

    public Players(Player... players) {
        this.players = Arrays.asList(players);
    }

    public Player next() {
        if (currentPlayerIndex != players.size()) {
            return players.get(currentPlayerIndex++);
        } else {
            currentPlayerIndex = STARTING_INDEX;
            return players.get(currentPlayerIndex);
        }
    }
}
