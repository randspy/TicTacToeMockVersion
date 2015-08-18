package com.randspy.tictactoe;

import java.util.Optional;

public class HumanPlayer implements Player {
    private Display display;

    public HumanPlayer(Display display) {
        this.display = display;
    }

    @Override
    public Optional<Board> makesMove(Board board) {

        display.displayInvalidMove();
        return null;
    }
}
