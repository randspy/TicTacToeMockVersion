package com.randspy.test.tictactoe;

import com.randspy.tictactoe.Board;
import com.randspy.tictactoe.Game;
import com.randspy.tictactoe.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlayingGameTest {

    @Test
    public void playerMakesValidMove() {

        Player player = mock(Player.class);

        Optional<Board> board = Optional.of(new Board());
        when(player.makesMove()).thenReturn(board, withNoBoardGameIsFinished());

        Game game = new Game(player);
        game.play();
        verify(player, times(2)).makesMove();
    }

    private Optional<Board> withNoBoardGameIsFinished() {
        return Optional.<Board>empty();
    }

    @Test
    public void playerMakesManyValidMoves() {

        Player player = mock(Player.class);

        Optional<Board> board = Optional.of(new Board());
        when(player.makesMove()).thenReturn(board, board, withNoBoardGameIsFinished());

        Game game = new Game(player);
        game.play();
        verify(player, times(3)).makesMove();
    }
}