package com.randspy.test.tictactoe.logic;

import com.randspy.tictactoe.logic.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GettingGameResultTest {

    private Board board;
    private GameResultDecider gameResultDecider;

    private void setRowWithWinningPlayer(PlayerId playerId, int rowNumber) {
        for (int idx = 0; idx < board.getDimension(); idx++) {
            board.setPlayerAtPosition(playerId, new PositionOnBoard(rowNumber, idx));
        }
    }

    private void setColumnWithWinningPlayer(PlayerId playerId, int columnNumber) {
        for (int idx = 0; idx < board.getDimension(); idx++) {
            board.setPlayerAtPosition(playerId, new PositionOnBoard(idx, columnNumber));
        }
    }

    private void setDiagonalFromLeftToRightWithWinningPlayer(PlayerId playerId) {
        for (int idx = 0; idx < board.getDimension(); idx++) {
            board.setPlayerAtPosition(playerId, new PositionOnBoard(idx, idx));
        }
    }

    private void setDiagonalFromRightToLeftWithWinningPlayer(PlayerId playerId) {
        int idy = board.getDimension() - 1;
        for (int idx = 0; idx < board.getDimension(); idx++, idy--) {
                board.setPlayerAtPosition(playerId, new PositionOnBoard(idx, idy));
        }
    }

    @Before
    public void setUp() throws Exception {
        board = new Board();
        gameResultDecider = new GameResultDecider();
    }


    @Test
    public void noWinner() {
        assertFalse(gameResultDecider.winnerIs(board).isPresent());
    }

    @Test
    public void whenFirstRowBelongsToOnePlayerHeIsTheWinner() {

        PlayerId playerId = new PlayerId();

        setRowWithWinningPlayer(playerId, 0);

        Optional<PlayerId> winner = gameResultDecider.winnerIs(board);
        assertTrue(winner.isPresent());
        assertEquals(playerId, winner.get());
    }

    @Test
    public void whenSecondRowBelongsToOnePlayerHeIsTheWinner() {

        PlayerId playerId = new PlayerId();

        setRowWithWinningPlayer(playerId, 1);

        Optional<PlayerId> winner = gameResultDecider.winnerIs(board);
        assertTrue(winner.isPresent());
        assertEquals(playerId, winner.get());
    }

    @Test
    public void whenColumnBelongsToOnePlayerHeIsTheWinner() {

        PlayerId playerId = new PlayerId();

        setColumnWithWinningPlayer(playerId, 1);

        Optional<PlayerId> winner = gameResultDecider.winnerIs(board);
        assertTrue(winner.isPresent());
        assertEquals(playerId, winner.get());
    }

    @Test
    public void whenDiagonalFromLeftToRightBelongsToOnePlayerHeIsTheWinner() {

        PlayerId playerId = new PlayerId();

        setDiagonalFromLeftToRightWithWinningPlayer(playerId);

        Optional<PlayerId> winner = gameResultDecider.winnerIs(board);
        assertTrue(winner.isPresent());
        assertEquals(playerId, winner.get());
    }

    @Test
    public void whenDiagonalFromRightToLeftBelongsToOnePlayerHeIsTheWinner() {

        PlayerId playerId = new PlayerId();

        setDiagonalFromRightToLeftWithWinningPlayer(playerId);

        Optional<PlayerId> winner = gameResultDecider.winnerIs(board);
        assertTrue(winner.isPresent());
        assertEquals(playerId, winner.get());
    }

    @Test
    public void whenManyPlayers() {

        PlayerId winningPlayerId = new PlayerId();
        PlayerId losingPlayerId = new PlayerId();

        setRowWithWinningPlayer(winningPlayerId, 2);
        board.setPlayerAtPosition(losingPlayerId, new PositionOnBoard(0, 0));

        Optional<PlayerId> winner = gameResultDecider.winnerIs(board);
        assertTrue(winner.isPresent());
        assertEquals(winningPlayerId, winner.get());
    }

}