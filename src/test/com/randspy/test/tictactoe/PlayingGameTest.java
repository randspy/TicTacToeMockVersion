package com.randspy.test.tictactoe;

import com.randspy.tictactoe.Board;
import com.randspy.tictactoe.Game;
import com.randspy.tictactoe.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlayingGameTest {

    @Test
    public void playerMakesValidMove() {

        Player player = mock(Player.class);

        Board board = new Board();
        when(player.makesMove()).thenReturn(board);

        Game game = new Game(player);
        game.play();
        verify(player).makesMove();
    }
}
