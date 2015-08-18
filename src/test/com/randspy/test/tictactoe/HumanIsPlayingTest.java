package com.randspy.test.tictactoe;

import com.randspy.tictactoe.Board;
import com.randspy.tictactoe.Display;
import com.randspy.tictactoe.HumanPlayer;
import com.randspy.tictactoe.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HumanIsPlayingTest {

    private Player player;
    @Mock
    private Display display;

    @Before
    public void setUp() throws Exception {
        player = new HumanPlayer(display);
    }

    @Test
    public void invalidMove() {
        final Board UNUSED_BOARD = null;

        player.makesMove(UNUSED_BOARD);
        verify(display).displayInvalidMove();
    }
}
