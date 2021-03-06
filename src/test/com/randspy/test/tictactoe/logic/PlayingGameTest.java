package com.randspy.test.tictactoe.logic;

import com.randspy.tictactoe.logic.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
public class PlayingGameTest {

    private Game game;
    @Mock private Player player;
    private Board board;
    private GameState gameState;

    private GameState withNoBoardGameIsFinished() {
        return new GameState(board, GameProgress.Finished);
    }

    @Before
    public void setUp() throws Exception {
        board = new Board();
        gameState = new GameState(board, GameProgress.InProgress);
        game = new Game(new PlayersCircularList(player), board);
    }

    @Test
    public void whenNoMoreMovesLeftExit() {
        when(player.makesMove(board)).thenReturn(withNoBoardGameIsFinished());

        game.play();
        verify(player).makesMove(gameState.board());
    }

    @Test
    public void playerMakesValidMove() {
        when(player.makesMove(board))
                .thenReturn(gameState, withNoBoardGameIsFinished());

        game.play();
        verify(player, times(2)).makesMove(gameState.board());
    }

    @Test
    public void playerMakesManyValidMoves() {
        when(player.makesMove(board))
                .thenReturn(gameState, gameState, withNoBoardGameIsFinished());

        game.play();
        verify(player, times(3)).makesMove(gameState.board());
    }

    @Test
    public void manyPlayersMakeMove() {
        Player otherPlayer = mock(Player.class);
        game = new Game(new PlayersCircularList(player, otherPlayer), board);

        when(player.makesMove(board))
                .thenReturn(gameState, withNoBoardGameIsFinished());

        when(otherPlayer.makesMove(board))
                .thenReturn(gameState);

        game.play();

        verify(player, times(2)).makesMove(gameState.board());
        verify(otherPlayer, times(1)).makesMove(gameState.board());
    }

    @Test
    public void boardFromPlayerMoveShouldBeUsedAsInputForNextMove() {
        Board boardAfterMove = new Board();
        GameState gameStateAfterMove = new GameState(boardAfterMove, GameProgress.InProgress);

        when(player.makesMove(board))
                .thenReturn(gameStateAfterMove);

        when(player.makesMove(boardAfterMove))
                .thenReturn(withNoBoardGameIsFinished());

        game.play();
        verify(player).makesMove(gameState.board());
        verify(player).makesMove(gameStateAfterMove.board());
    }
}