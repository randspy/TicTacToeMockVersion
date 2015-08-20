package com.randspy.test.tictactoe.logic;

import com.randspy.tictactoe.logic.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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
    private GameResult gameResult;

    private void fillBoardToFullExceptFirstOne() {
        for (int idx = 0; idx < board.getDimension(); idx++) {
            for (int idy = 0; idy < board.getDimension(); idy++) {
                board.setPlayerAtPosition(player.getId(), new PositionOnBoard(idx, idy));
            }
        }

        board.setPlayerAtPosition(null, new PositionOnBoard(0, 0));
    }

    @Before
    public void setUp() throws Exception {
        player = new ComputerPlayer(ai, display, gameResult);
        board = new Board();
    }

    @Test
    public void validMove() {

        when(ai.move(board)).thenReturn(new PositionOnBoard(1, 1));
        when(gameResult.winnerIs(board)).thenReturn(Optional.empty());

        Board resultBoard = player.makesMove(board).get();

        assertEquals(resultBoard.getPlayerAtPosition(new PositionOnBoard(1, 1)), player.getId());

        verify(display).displayBoard(board);
    }

    @Test
    public void winningGame() {

        when(ai.move(board)).thenReturn(new PositionOnBoard(1, 1));
        when(gameResult.winnerIs(board)).thenReturn(Optional.of(player.getId()));

        assertFalse(player.makesMove(board).isPresent());
        verify(display).displayBoard(board);
        verify(display).displayPlayerWon(player.getId());
    }

    @Test
    public void loosingGame() {

        when(ai.move(board)).thenReturn(new PositionOnBoard(1, 1));
        PlayerId otherPlayerId = new PlayerId();
        when(gameResult.winnerIs(board)).thenReturn(Optional.of(otherPlayerId));

        assertFalse(player.makesMove(board).isPresent());
        verify(display).displayBoard(board);
        verify(display).displayPlayerWon(otherPlayerId);
    }

    @Test
    public void tieGame() {

        when(ai.move(board)).thenReturn(new PositionOnBoard(0, 0));
        when(gameResult.winnerIs(board)).thenReturn(Optional.empty());

        fillBoardToFullExceptFirstOne();

        assertFalse(player.makesMove(board).isPresent());
        verify(display).displayBoard(board);
        verify(display).displayTie();
    }
}
