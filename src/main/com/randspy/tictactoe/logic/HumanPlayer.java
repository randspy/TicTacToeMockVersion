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

        display.displayInstructions();
        askUserForPosition();

        askForUserInputUntilValidPosition();
        askForUserInputUntilPositionIsNotOccupied();

        return reactionOnValidInput();
    }

    private void askUserForPosition() {
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

    private boolean isValidInput() {
        return isInRange(row) && isInRange(column);
    }

    private boolean isInRange(int position) {
        return position >= 0 && position < board.getDimension();
    }

    private void askForUserInputUntilValidPosition() {
        while(!isValidInput()) {
            display.displayInvalidMove();
            display.displayInstructions();
            askUserForPosition();
        }
    }

    private void askForUserInputUntilPositionIsNotOccupied() {
        while (board.getPlayerAtPosition(new PositionOnBoard(row, column)) != null) {
            display.displayFieldIsOccupied();
            display.displayInstructions();
            askUserForPosition();
        }
    }
    
    // TODO REFACTOR this method is possibly doing to many things. Weird name indicates it.
    private Optional<Board> reactionOnValidInput() {

        board.setPlayerAtPosition(getId(), new PositionOnBoard(row, column));
        display.displayBoard(this.board);

        // TODO REFACTOR into more functional style
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
}
