package com.randspy.tictactoe.logic;

import java.util.Optional;

public class HumanPlayer extends Player {
    private Display display;
    private UserInput userInput;
    private GameResult gameResult;
    private Board board;
    private int row;
    private int column;

    public HumanPlayer(Display display, UserInput userInput, GameResult gameResult) {
        this.display = display;
        this.userInput = userInput;
        this.gameResult = gameResult;
    }

    @Override
    public Optional<Board> makesMove(Board board) {

        this.board = board;

        retrievePosition();

        if(isValidMove())
        {
            this.board.setPlayerAtPosition(getId(), new PositionOnBoard(0, 0));
            display.displayBoard(this.board);

            Optional<PlayerId> winner = gameResult.winnerIs(this.board);
            if (winner.isPresent()) {
                display.displayPlayerWon(winner.get());
                return Optional.empty();
            }
            else if (this.board.isFull()) {
                display.displayTie();
                return Optional.empty();
            } else {
                return Optional.of(this.board);
            }
        }
        else {
            display.displayInvalidMove();
        }

        return null;
    }

    private void retrievePosition() {
        int boardStartingPosition = 1;
        int position = toNumber(userInput.getText()) - boardStartingPosition;

        row = position / this.board.getDimension();
        column = position % this.board.getDimension();
    }

    public int toNumber(String input) {
        try {
            return new Integer(input);
        } catch (java.lang.NumberFormatException e) {
            return 0;
        }
    }

    private boolean isValidMove() {
        return isInRange(row) && isInRange(column);
    }

    private boolean isInRange(int position) {
        return position >= 0 && position < board.getDimension();
    }
}
