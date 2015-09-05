package com.randspy.test.tictactoe.logic;

import com.randspy.tictactoe.logic.*;
import org.hamcrest.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static com.randspy.test.tictactoe.logic.OptionalMatcher.isEmpty;
import static com.randspy.test.tictactoe.logic.OptionalMatcher.isPresentAnd;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

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
        assertThat(gameResultDecider.winnerIs(board), isEmpty());
    }

    @Test
    public void whenFirstRowBelongsToOnePlayerHeIsTheWinner() {

        PlayerId playerId = new PlayerId();

        setRowWithWinningPlayer(playerId, 0);

        Optional<PlayerId> winner = gameResultDecider.winnerIs(board);
        assertThat(winner, isPresentAnd(is(playerId)));
    }

    @Test
    public void whenSecondRowBelongsToOnePlayerHeIsTheWinner() {

        PlayerId playerId = new PlayerId();

        setRowWithWinningPlayer(playerId, 1);

        Optional<PlayerId> winner = gameResultDecider.winnerIs(board);
        assertThat(winner, isPresentAnd(is(playerId)));
    }

    @Test
    public void whenColumnBelongsToOnePlayerHeIsTheWinner() {

        PlayerId playerId = new PlayerId();

        setColumnWithWinningPlayer(playerId, 1);

        Optional<PlayerId> winner = gameResultDecider.winnerIs(board);
        assertThat(winner, isPresentAnd(is(playerId)));
    }

    @Test
    public void whenDiagonalFromLeftToRightBelongsToOnePlayerHeIsTheWinner() {

        PlayerId playerId = new PlayerId();

        setDiagonalFromLeftToRightWithWinningPlayer(playerId);

        Optional<PlayerId> winner = gameResultDecider.winnerIs(board);
        assertThat(winner, isPresentAnd(is(playerId)));
    }

    @Test
    public void whenDiagonalFromRightToLeftBelongsToOnePlayerHeIsTheWinner() {

        PlayerId playerId = new PlayerId();

        setDiagonalFromRightToLeftWithWinningPlayer(playerId);

        Optional<PlayerId> winner = gameResultDecider.winnerIs(board);
        assertThat(winner, isPresentAnd(is(playerId)));
    }

    @Test
    public void whenManyPlayers() {

        PlayerId winningPlayerId = new PlayerId();
        PlayerId losingPlayerId = new PlayerId();

        setRowWithWinningPlayer(winningPlayerId, 2);
        board.setPlayerAtPosition(losingPlayerId, new PositionOnBoard(0, 0));

        Optional<PlayerId> winner = gameResultDecider.winnerIs(board);
        assertThat(winner, isPresentAnd(is(winningPlayerId)));
    }

}