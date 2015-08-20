package com.randspy.test.tictactoe.logic;

import com.randspy.tictactoe.logic.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ComputerIsPlayingTest {

    private Player player;
    private Board board;
    @Mock
    private MinMax ai;
    @Mock
    private Display display;

    @Before
    public void setUp() throws Exception {
        player = new ComputerPlayer(ai, display);
        board = new Board();
    }

    @Test
    public void validMove() {

        when(ai.move(board)).thenReturn(new PositionOnBoard(1, 1));
        Board resultBoard = player.makesMove(board).get();

        assertEquals(resultBoard.getPlayerAtPosition(new PositionOnBoard(1, 1)), player.getId());

        verify(display).displayBoard(board);
    }
}
