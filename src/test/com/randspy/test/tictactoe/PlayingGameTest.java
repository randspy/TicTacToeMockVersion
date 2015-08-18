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

@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
public class PlayingGameTest {

    private Game game;
    @Mock private Player player;
    private Board board;
    private Optional<Board> resultBoard;

    private Optional<Board> withNoBoardGameIsFinished() {
        return Optional.<Board>empty();
    }

    @Before
    public void setUp() throws Exception {
        board = new Board();
        resultBoard = Optional.of(board);
        game = new Game(new Players(player), board);
    }

    @Test
    public void whenNoMoreMovesLeftExit() {
        when(player.makesMove(board)).thenReturn(withNoBoardGameIsFinished());

        game.play();
        verify(player).makesMove(resultBoard.get());
    }

    @Test
    public void playerMakesValidMove() {
        when(player.makesMove(resultBoard.get()))
                .thenReturn(resultBoard, withNoBoardGameIsFinished());

        game.play();
        verify(player, times(2)).makesMove(resultBoard.get());
    }

    @Test
    public void playerMakesManyValidMoves() {
        when(player.makesMove(resultBoard.get()))
                .thenReturn(resultBoard, resultBoard, withNoBoardGameIsFinished());

        game.play();
        verify(player, times(3)).makesMove(resultBoard.get());
    }

    @Test
    public void manyPlayersMakeMove() {
        Player otherPlayer = mock(Player.class);
        game = new Game(new Players(player, otherPlayer), board);

        when(player.makesMove(resultBoard.get()))
                .thenReturn(resultBoard, withNoBoardGameIsFinished());

        when(otherPlayer.makesMove(resultBoard.get()))
                .thenReturn(resultBoard);

        game.play();

        verify(player, times(2)).makesMove(resultBoard.get());
        verify(otherPlayer, times(1)).makesMove(resultBoard.get());
    }

    @Test
    public void boardFromPlayerMoveShouldBeUsedAsInputForNextMove() {
        Optional<Board> resultBoardAfterPlayerMove = Optional.of(new Board());

        when(player.makesMove(resultBoard.get()))
                .thenReturn(resultBoardAfterPlayerMove);

        when(player.makesMove(resultBoardAfterPlayerMove.get()))
                .thenReturn(withNoBoardGameIsFinished());

        game.play();
        verify(player).makesMove(resultBoard.get());
        verify(player).makesMove(resultBoardAfterPlayerMove.get());

    }
}