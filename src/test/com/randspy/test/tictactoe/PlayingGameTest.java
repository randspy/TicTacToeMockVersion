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
    @Mock private Player player;
    private Board board;
    private Optional<Board> optionalBoard;

    private Optional<Board> withNoBoardGameIsFinished() {
        return Optional.<Board>empty();
    }

    @Before
    public void setUp() throws Exception {
        board = new Board();
        optionalBoard = Optional.of(board);
        game = new Game.Builder()
                .withBoard(board)
                .withPlayer(player)
                .build();
    }

    @Test
    public void whenNoMoreMovesLeftExit() {
        when(player.makesMove(board)).thenReturn(withNoBoardGameIsFinished());

        game.play();
        verify(player).makesMove(optionalBoard.get());
    }

    @Test
    public void playerMakesValidMove() {
        when(player.makesMove(optionalBoard.get()))
                .thenReturn(optionalBoard, withNoBoardGameIsFinished());

        game.play();
        verify(player, times(2)).makesMove(optionalBoard.get());
    }

    @Test
    public void playerMakesManyValidMoves() {
        when(player.makesMove(optionalBoard.get()))
                .thenReturn(optionalBoard, optionalBoard, withNoBoardGameIsFinished());

        game.play();
        verify(player, times(3)).makesMove(optionalBoard.get());
    }
}