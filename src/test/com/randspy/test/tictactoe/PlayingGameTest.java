package com.randspy.test.tictactoe;

import com.randspy.tictactoe.Board;
import com.randspy.tictactoe.Game;
import com.randspy.tictactoe.Player;
import com.randspy.tictactoe.Players;
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
        game = new Game(new Players(player), board);
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

    @Test
    public void manyPlayersMakeMove() {
        Player otherPlayer = mock(Player.class);
        game = new Game(new Players(player, otherPlayer), board);

        when(player.makesMove(optionalBoard.get()))
                .thenReturn(optionalBoard, withNoBoardGameIsFinished());

        when(otherPlayer.makesMove(optionalBoard.get()))
                .thenReturn(optionalBoard);

        game.play();

        verify(player, times(2)).makesMove(optionalBoard.get());
        verify(otherPlayer, times(1)).makesMove(optionalBoard.get());
    }

    @Test
    public void boardFromPlayerMoveShouldBeUsedAsInputForNextMove() {

        game = new Game(new Players(player), board);
        Optional<Board> otherOptionalBoard = Optional.of(new Board());

        when(player.makesMove(optionalBoard.get()))
                .thenReturn(otherOptionalBoard);

        when(player.makesMove(otherOptionalBoard.get()))
                .thenReturn(withNoBoardGameIsFinished());

        game.play();
        verify(player).makesMove(optionalBoard.get());
        verify(player).makesMove(otherOptionalBoard.get());

    }
}