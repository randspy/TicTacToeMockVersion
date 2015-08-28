package com.randspy.tictactoe.logic;

import java.util.Optional;

public class GameState {

    private final Optional<Board> board;

    public GameState(Optional<Board> board) {
        this.board = board;
    }

    public Optional<Board> board() {
        return board;
    }

    public boolean isPresent() {
        return board.isPresent();
    }

    public Board get() {
        return board.get();
    }
}
