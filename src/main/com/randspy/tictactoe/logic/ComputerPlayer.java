package com.randspy.tictactoe.logic;

import java.util.Optional;

public class ComputerPlayer extends Player {
    private MinMax ai;

    public ComputerPlayer(Player.Builder builder, MinMax ai) {
        super(builder);
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
