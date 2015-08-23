package com.randspy.test.tictactoe.logic;

import com.randspy.tictactoe.logic.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ComputerIsPlayingTest {

    private Player player;
    private Board board;
    @Mock
    private MinMax ai;
    @Mock
    private Display display;
    @Mock
    private GameResult gameResult;

    private void fillBoardToFullExceptFirstOne() {
        for (int idx = 0; idx < board.getDimension(); idx++) {
            for (int idy = 0; idy < board.getDimension(); idy++) {
                board.setPlayerAtPosition(player.getId(), new PositionOnBoard(idx, idy));
            }
        }

        board.setPlayerAtPosition(null, new PositionOnBoard(0, 0));
    }

    private void expectWinner(Optional<PlayerId> winner) {
        when(gameResult.winnerIs(board)).thenReturn(winner);
    }

    private void noMoreMovesInGame() {
        assertFalse(player.makesMove(board).isPresent());
    }

    @Before
    public void setUp() throws Exception {
        player = new ComputerPlayer(new PlayerId(), display, gameResult, ai);
        board = new Board();
    }

    @Test
    public void validMove() {

        expectMoveFromAI(1, 1);
        expectWinner(Optional.empty());

        Board resultBoard = player.makesMove(board).get();

        assertEquals(resultBoard.getPlayerAtPosition(new PositionOnBoard(1, 1)), player.getId());

        verify(display).displayBoard(board);
    }

    private OngoingStubbing<PositionOnBoard> expectMoveFromAI(int row, int column) {
        return when(ai.move(board)).thenReturn(new PositionOnBoard(row, column));
    }

    @Test
    public void winningGame() {

        expectMoveFromAI(1, 1);
        expectWinner(Optional.of(player.getId()));

        noMoreMovesInGame();
        verify(display).displayBoard(board);
        verify(display).displayPlayerWon(player.getId());
    }

    @Test
    public void loosingGame() {

        expectMoveFromAI(1, 1);
        PlayerId otherPlayerId = new PlayerId();
        expectWinner(Optional.of(otherPlayerId));

        noMoreMovesInGame();
        verify(display).displayBoard(board);
        verify(display).displayPlayerWon(otherPlayerId);
    }

    @Test
    public void tieGame() {

        expectMoveFromAI(0, 0);
        expectWinner(Optional.empty());

        fillBoardToFullExceptFirstOne();

        noMoreMovesInGame();
        verify(display).displayBoard(board);
        verify(display).displayTie();
    }
}
