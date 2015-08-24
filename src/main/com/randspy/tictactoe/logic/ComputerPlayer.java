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

        board.setPlayerAtPosition(playerId, ai.move(board));
        Optional<PlayerId> winner = gameResult.winnerIs(board);

        display.displayBoard(board);

        if (winner.isPresent()) {
            display.displayPlayerWon(winner.get());
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
