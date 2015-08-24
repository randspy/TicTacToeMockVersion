package com.randspy.test.tictactoe.logic;

import com.randspy.tictactoe.logic.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ComputerIsPlayingTest {

    private Player player;
    private Board board;
    @Mock
    private MinMax ai;
    @Mock
    private Display display;
    @Mock
    private GameResultDecider gameResultDecider;
    private PlayerId playerId;

    private void fillBoardToFullExceptFirstOne() {
        for (int idx = 0; idx < board.getDimension(); idx++) {
            for (int idy = 0; idy < board.getDimension(); idy++) {
                board.setPlayerAtPosition(playerId, new PositionOnBoard(idx, idy));
            }
        }

        board.setPlayerAtPosition(null, new PositionOnBoard(0, 0));
    }

    private void expectWinner(Optional<PlayerId> winner) {
        when(gameResultDecider.winnerIs(board)).thenReturn(winner);
    }

    private void noMoreMovesInGame() {
        assertFalse(player.makesMove(board).isPresent());
    }

    @Before
    public void setUp() throws Exception {
        playerId = new PlayerId();
        player = new ComputerPlayer(
                new Player.Builder()
                        .withPlayerId(playerId)
                        .withDisplay(display)
                        .withGameResultDecider(gameResultDecider),
                ai);

        board = new Board();
    }

    @Test
    public void validMove() {

        expectMoveFromAI(1, 1);
        expectWinner(Optional.empty());

        Board resultBoard = player.makesMove(board).get();

        assertEquals(resultBoard.getPlayerAtPosition(new PositionOnBoard(1, 1)), playerId);

        verify(display).board(board);
    }

    private OngoingStubbing<PositionOnBoard> expectMoveFromAI(int row, int column) {
        return when(ai.move(board)).thenReturn(new PositionOnBoard(row, column));
    }

    @Test
    public void winningGame() {

        expectMoveFromAI(1, 1);
        expectWinner(Optional.of(playerId));

        noMoreMovesInGame();
        verify(display).board(board);
        verify(display).playerWon(playerId);
    }

    @Test
    public void loosingGame() {

        expectMoveFromAI(1, 1);
        PlayerId otherPlayerId = new PlayerId();
        expectWinner(Optional.of(otherPlayerId));

        noMoreMovesInGame();
        verify(display).board(board);
        verify(display).playerWon(otherPlayerId);
    }

    @Test
    public void tieGame() {

        expectMoveFromAI(0, 0);
        expectWinner(Optional.empty());

        fillBoardToFullExceptFirstOne();

        noMoreMovesInGame();
        verify(display).board(board);
        verify(display).tie();
    }
}
