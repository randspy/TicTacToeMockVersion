package com.randspy.tictactoe.logic;

import java.util.Optional;

public class GameState {

    private final Board board;
    private final GameProgress gameProgress;

    public GameState(Board board, GameProgress gameProgress) {
        this.board = board;
        this.gameProgress = gameProgress;
    }

    public Optional<Board> board() {
        return isPresent() ? Optional.ofNullable(board) : Optional.<Board>empty();
    }

    public boolean isPresent() {
        return gameProgress == GameProgress.InProgress;
    }

    public Board get() {
        return board;
    }
}
