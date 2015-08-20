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
public class HumanIsPlayingTest {

    private Player player;
    @Mock
    private Display display;
    @Mock
    private UserInput userInput;
    private Board board;
    @Mock
    private GameResult gameResult;

    private void noMoreMovesInGame() {
        assertFalse(player.makesMove(board).isPresent());
    }

    private void expectUserInputs(String value, String... values) {
        when(userInput.getText()).thenReturn(value, values);
    }

    @Before
    public void setUp() throws Exception {
        player = new HumanPlayer(display, userInput, gameResult);
        board = new Board();
    }

    private OngoingStubbing<Optional<PlayerId>> expectWinner(Optional<PlayerId> value) {
        return when(gameResult.winnerIs(board)).thenReturn(value);
    }

    private void fillBoardToFullExceptFirstOne() {
        for (int idx = 0; idx < board.getDimension(); idx++) {
            for (int idy = 0; idy < board.getDimension(); idy++) {
                board.setPlayerAtPosition(player.getId(), new PositionOnBoard(idx, idy));
            }
        }

        board.setPlayerAtPosition(null, new PositionOnBoard(0, 0));
    }

    @Test
    public void invalidMoveIsFallowedByValidOne() {

        expectUserInputs("0", "1");
        Optional<PlayerId> value = Optional.empty();
        expectWinner(value);

        player.makesMove(board);

        verify(display, times(2)).displayInstructions();
        verify(display).displayInvalidMove();
        verify(display).displayBoard(board);
    }

    @Test
    public void invalidInputIsFallowedByValidOne() {

        expectUserInputs("p", "1");
        expectWinner(Optional.empty());

        player.makesMove(board);
        verify(display, times(2)).displayInstructions();
        verify(display).displayInvalidMove();
        verify(display).displayBoard(board);
    }

    @Test
    public void alreadyOccupiedFieldIsFallowedByValidOne() {

        expectUserInputs("2", "3");
        expectWinner(Optional.empty());

        board.setPlayerAtPosition(player.getId(), new PositionOnBoard(0, 1));

        player.makesMove(board);
        verify(display, times(2)).displayInstructions();
        verify(display).displayFieldIsOccupied();
        verify(display).displayBoard(board);
    }

    @Test
    public void alreadyOccupiedFieldIsFallowedByInvalidOne() {

        expectUserInputs("2", "p", "3");
        expectWinner(Optional.empty());

        board.setPlayerAtPosition(player.getId(), new PositionOnBoard(0, 1));

        player.makesMove(board);
        verify(display, times(3)).displayInstructions();
        verify(display).displayFieldIsOccupied();
        verify(display).displayInvalidMove();
        verify(display).displayBoard(board);
    }

    @Test
    public void validMove(){

        expectUserInputs("5");
        expectWinner(Optional.empty());

        Board resultBoard = player.makesMove(board).get();

        assertEquals(resultBoard.getPlayerAtPosition(new PositionOnBoard(1, 1)), player.getId());

        verify(display).displayInstructions();
        verify(display).displayBoard(board);
    }

    @Test public void winningGame() {
        expectUserInputs("1");
        expectWinner(Optional.of(player.getId()));

        noMoreMovesInGame();

        verify(display).displayInstructions();
        verify(display).displayBoard(board);
        verify(display).displayPlayerWon(player.getId());
    }

    @Test public void loosingGame() {
        expectUserInputs("1");
        PlayerId otherPlayerId = new PlayerId();
        expectWinner(Optional.of(otherPlayerId));

        noMoreMovesInGame();

        verify(display).displayInstructions();
        verify(display).displayBoard(board);
        verify(display).displayPlayerWon(otherPlayerId);
    }

    @Test public void tieGame() {
        expectUserInputs("1");
        expectWinner(Optional.empty());

        fillBoardToFullExceptFirstOne();

        noMoreMovesInGame();

        verify(display).displayInstructions();
        verify(display).displayBoard(board);
        verify(display).displayTie();
    }
}
