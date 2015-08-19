package com.randspy.tictactoe.logic;

import com.randspy.tictactoe.logic.Board;import com.randspy.tictactoe.logic.PlayerId;import java.util.Arrays;
import java.util.Optional;

public class GameResult {
    private Optional<PlayerId> result;
    private Board board;

    public Optional<PlayerId> winnerIs(Board board) {

        init(board);

        for(PlayerId playerId : board.getPresentPlayers())
        {
            winnerInRow(playerId);
            winnerInColumn(playerId);
            winnerInDiagonalFromLeftToRight(playerId);
            winnerInDiagonalFromRightToLeft(playerId);
        }

        return result;
    }

    private void init(Board board) {
        result = Optional.ofNullable(null);
        this.board = board;
    }

    private void winnerInDiagonalFromRightToLeft(PlayerId playerId) {
        if (doesSequenceBelongToOnePlayer(board.getPlayersAtDiagonalFromRightToLeft(), playerId)) {
            result = Optional.of(playerId);
        }
    }

    private void winnerInDiagonalFromLeftToRight(PlayerId playerId) {
        if (doesSequenceBelongToOnePlayer(board.getPlayersAtDiagonalFromLeftToRight(), playerId)) {
            result = Optional.of(playerId);
        }
    }

    private void winnerInColumn(PlayerId playerId) {
        for (int idx = 0; idx < board.getDimension(); idx++) {
            if (doesSequenceBelongToOnePlayer(board.getPlayersAtColumn(idx), playerId)) {
                result = Optional.of(playerId);
            }
        }
    }

    private void winnerInRow(PlayerId playerId) {
        for (int idx = 0; idx < board.getDimension(); idx++) {
            if (doesSequenceBelongToOnePlayer(board.getPlayersAtRow(idx), playerId)) {
                result = Optional.of(playerId);
            }
        }
    }

    private boolean doesSequenceBelongToOnePlayer(PlayerId[] playersIdsAtRow, PlayerId playerId) {
        return Arrays.stream(playersIdsAtRow)
                .filter(field -> field != null && field.equals(playerId))
                .count() == board.getDimension();
    }

}
