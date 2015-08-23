package com.randspy.tictactoe.logic;

import java.util.Optional;

public class ComputerPlayer extends Player {
    private MinMax ai;
    private Display display;
    private GameResult gameResult;

    public ComputerPlayer(PlayerId playerId, Display display, GameResult gameResult, MinMax ai) {
        // TODO : maybe display and gameResult should be located in Player class
        // it would reduce code duplication, but from other end it would create an additional coupling
        super(playerId);
        this.display = display;
        this.gameResult = gameResult;
        this.ai = ai;
    }

    @Override
    public Optional<Board> makesMove(Board board) {

        board.setPlayerAtPosition(getId(), ai.move(board));
        Optional<PlayerId> playerId = gameResult.winnerIs(board);

        display.displayBoard(board);

        if (playerId.isPresent()) {
            display.displayPlayerWon(playerId.get());
            return Optional.empty();
        }
        else if (board.isFull()){
            display.displayTie();
            return Optional.empty();
        }
        else {
            return Optional.of(board);
        }
    }
}
