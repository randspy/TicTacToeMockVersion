package com.randspy.tictactoe.logic;

import java.util.Optional;

public class GameState {

    private final Board board;
    private final GameProgress gameProgress;

    public GameState(Board board, GameProgress gameProgress) {
        this.board = board;
        this.gameProgress = gameProgress;
    }

    public boolean isInProgress() {
        return gameProgress == GameProgress.InProgress;
    }

    public Board board() {
        return board;
    }
}
