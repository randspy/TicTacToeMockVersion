package com.randspy.tictactoe.logic;

import com.randspy.tictactoe.logic.Player;

import java.util.Arrays;
import java.util.List;

public class PlayersCircularList {
    private static final int STARTING_INDEX = 0;

    private List<Player> players;
    private int currentPlayerIndex = STARTING_INDEX;

    public PlayersCircularList(Player... players) {
        this.players = Arrays.asList(players);
    }

    public Player next() {
        if (currentPlayerIndex != players.size()) {
            return players.get(currentPlayerIndex++);
        } else {
            currentPlayerIndex = STARTING_INDEX;
            return players.get(currentPlayerIndex++);
        }
    }
}
