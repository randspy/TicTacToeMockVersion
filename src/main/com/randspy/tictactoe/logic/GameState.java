package com.randspy.tictactoe.logic;

import java.util.Optional;

public class GameState {

    private final Board board;
    private final GameProgress gameProgress;

    public GameState(Optional<Board> board) {
        this(board.orElse(null), board.isPresent() ? GameProgress.InProgress : GameProgress.Finished);
    }

    public GameState(Board board, GameProgress gameProgress) {

        this.board = board;
        this.gameProgress = gameProgress;
    }

    public Optional<Board> board() {
        return Optional.ofNullable(board);
    }

    public boolean isPresent() {
        return gameProgress == GameProgress.InProgress;
    }

    public Board get() {
        return board;
    }
}
