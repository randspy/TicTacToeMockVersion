package com.randspy.tictactoe.logic;

import java.util.Optional;

public class HumanPlayer extends Player {
    private Display display;
    private UserInput userInput;
    private Board board;

    public HumanPlayer(Display display, UserInput userInput) {
        this.display = display;
        this.userInput = userInput;
    }

    @Override
    public Optional<Board> makesMove(Board board) {

        this.board = board;

        int boardStartingPosition = 1;
        int position = toNumber(userInput.getText()) - boardStartingPosition;

        int row = position / board.getDimension();
        int column = position % board.getDimension();

        if(isValidMove(row, column))
        {
            board.setPlayerAtPosition(getId(), new PositionOnBoard(0,0));
            display.displayBoard(board);
            return Optional.of(board);
        }
        else {
            display.displayInvalidMove();
        }

        return null;
    }

    public int toNumber(String input) {
        return new Integer(input);
    }

    private boolean isValidMove(int row, int column) {
        return isInRange(row) && isInRange(column);
    }

    private boolean isInRange(int position) {
        return position >= 0 && position < board.getDimension();
    }
}
