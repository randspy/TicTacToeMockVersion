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
public class HumanIsPlayingTest {

    private Player player;
    @Mock
    private Display display;
    @Mock
    private UserInput userInput;
    private Board board;
    @Mock
    private GameResult gameResult;

    @Before
    public void setUp() throws Exception {
        player = new HumanPlayer(display, userInput, gameResult);
        board = new Board();
    }

    @Test
    public void invalidMove() {

        when(userInput.getText()).thenReturn("0");

        player.makesMove(board);
        verify(display).displayInvalidMove();
    }

    @Test public void validMove(){

        when(userInput.getText()).thenReturn("1");
        when(gameResult.winnerIs(board)).thenReturn(Optional.empty());

        Board resultBoard = player.makesMove(board).get();

        assertEquals(resultBoard.getPlayerAtPosition(new PositionOnBoard(0, 0)), player.getId());
        verify(display).displayBoard(board);
    }

    @Test public void winningGame() {
        when(userInput.getText()).thenReturn("1");
        when(gameResult.winnerIs(board)).thenReturn(Optional.of(player.getId()));

        assertFalse(player.makesMove(board).isPresent());

        verify(display).displayBoard(board);
        verify(display).displayPlayerWon(player.getId());
    }

    @Test public void loosingGame() {
        when(userInput.getText()).thenReturn("1");
        PlayerId otherPlayerId = new PlayerId();
        when(gameResult.winnerIs(board)).thenReturn(Optional.of(otherPlayerId));

        assertFalse(player.makesMove(board).isPresent());

        verify(display).displayBoard(board);
        verify(display).displayPlayerWon(otherPlayerId);
    }
}
