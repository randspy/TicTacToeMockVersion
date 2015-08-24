package com.randspy.tictactoe.logic;

import java.util.Optional;

public class HumanPlayer extends Player {
    private UserInput userInput;
    private Board board;
    private int row;
    private int column;

    public HumanPlayer(Player.Builder builder, UserInput userInput) {
        super(builder);
        this.userInput = userInput;
    }

    @Override
    public Optional<Board> makesMove(Board board) {

        this.board = board;

        display.instructions(playerId);
        askUserForPosition();

        while (true) {
            if(!isValidInput()){
                invalidInput();
            }
            else if (isFieldAlreadyOccupied()){
                fieldAlreadyOccupied();
            }
            else{
                return validInput();
            }
        }
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

    private void invalidInput() {
        display.invalidMove();
        display.instructions(playerId);
        askUserForPosition();
    }

    private boolean isFieldAlreadyOccupied() {
        return this.board.getPlayerAtPosition(new PositionOnBoard(row, column)) != null;
    }

    private void fieldAlreadyOccupied() {
        display.fieldIsOccupied();
        display.instructions(playerId);
        askUserForPosition();
    }

    private Optional<Board> validInput() {

        board.setPlayerAtPosition(playerId, new PositionOnBoard(row, column));
        display.board(this.board);

        // TODO REFACTOR into more functional style
        Optional<PlayerId> winner = gameResultDecider.winnerIs(this.board);
        if (winner.isPresent()) {
            display.playerWon(winner.get());
            return Optional.empty();
        }
        else if (this.board.isFull()) {
            display.tie();
            return Optional.empty();
        } else {
            return Optional.of(this.board);
        }
    }
}
