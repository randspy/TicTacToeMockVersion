package com.randspy.tictactoe.logic;

import java.util.Optional;

public class HumanPlayer extends Player {
    private Display display;
    private UserInput userInput;
    private GameResult gameResult;
    private Board board;
    private int row;
    private int column;

    public static class Builder{

        private PlayerId playerId;
        private Display display;
        private GameResult gameResult;
        private UserInput userInput;

        public Builder withPlayerId(PlayerId playerId) {
            this.playerId = playerId;
            return this;
        }

        public Builder withDisplay(Display display) {
            this.display = display;
            return this;
        }

        public Builder withGameResultDecider(GameResult gameResult) {
            this.gameResult = gameResult;
            return this;
        }

        public Builder withUserInput(UserInput userInput) {
            this.userInput = userInput;
            return this;
        }

        public HumanPlayer build() {
            return new HumanPlayer(this);
        }

    }

    public HumanPlayer(Builder builder) {
        super(builder.playerId);
        this.display = builder.display;
        this.gameResult = builder.gameResult;
        this.userInput = builder.userInput;
    }

    @Override
    public Optional<Board> makesMove(Board board) {

        this.board = board;

        display.displayInstructions(getId());
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
        display.displayInvalidMove();
        display.displayInstructions(getId());
        askUserForPosition();
    }

    private boolean isFieldAlreadyOccupied() {
        return this.board.getPlayerAtPosition(new PositionOnBoard(row, column)) != null;
    }

    private void fieldAlreadyOccupied() {
        display.displayFieldIsOccupied();
        display.displayInstructions(getId());
        askUserForPosition();
    }

    private Optional<Board> validInput() {

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
