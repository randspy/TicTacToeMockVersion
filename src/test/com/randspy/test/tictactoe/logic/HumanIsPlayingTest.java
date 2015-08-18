package com.randspy.test.tictactoe.logic;

import com.randspy.tictactoe.logic.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HumanIsPlayingTest {

    private Player player;
    @Mock
    private Display display;
    @Mock
    private UserInput userInput;
    private Board board;

    @Before
    public void setUp() throws Exception {
        player = new HumanPlayer(display, userInput);
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

        Board resultBoard = player.makesMove(board).get();

        assertEquals(resultBoard.getPlayerAtPosition(new PositionOnBoard(0, 0)), player.getId());
        verify(display).displayBoard(board);
    }
}
