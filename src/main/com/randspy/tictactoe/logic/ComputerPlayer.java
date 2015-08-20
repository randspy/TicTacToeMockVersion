package com.randspy.tictactoe.logic;

import java.util.Optional;

public class ComputerPlayer extends Player {
    private MinMax ai;
    private Display display;

    public ComputerPlayer(MinMax ai, Display display) {
        this.ai = ai;
        this.display = display;
    }

    @Override
    public Optional<Board> makesMove(Board board) {
        board.setPlayerAtPosition(getId(), ai.move(board));
        display.displayBoard(board);
        return Optional.of(board);
    }
}
