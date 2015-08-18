package com.randspy.test.tictactoe;

import com.randspy.tictactoe.Board;
import com.randspy.tictactoe.Game;
import com.randspy.tictactoe.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlayingGameTest {

    private Game game;
    private Optional<Board> board;

    @Mock
    private Player player;

    private Optional<Board> withNoBoardGameIsFinished() {
        return Optional.<Board>empty();
    }

    @Before
    public void setUp() throws Exception {
        board = Optional.of(new Board());
        game = new Game(player);
    }

    @Test
    public void whenNoMoreMovesLeftExit() {
        when(player.makesMove()).thenReturn(withNoBoardGameIsFinished());

        game.play();
        verify(player).makesMove();
    }

    @Test
    public void playerMakesValidMove() {
        when(player.makesMove()).thenReturn(board, withNoBoardGameIsFinished());

        game.play();
        verify(player, times(2)).makesMove();
    }

    @Test
    public void playerMakesManyValidMoves() {
        when(player.makesMove()).thenReturn(board, board, withNoBoardGameIsFinished());

        game.play();
        verify(player, times(3)).makesMove();
    }
}