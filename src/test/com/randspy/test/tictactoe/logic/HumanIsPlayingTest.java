package com.randspy.test.tictactoe.logic;

import com.randspy.tictactoe.logic.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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
    private GameResultDecider gameResultDecider;
    private PlayerId playerId;

    private void noMoreMovesInGame() {
        assertFalse(player.makesMove(board).isPresent());
    }

    private void expectUserInputs(String value, String... values) {
        when(userInput.getText()).thenReturn(value, values);
    }

    @Before
    public void setUp() throws Exception {
        playerId = new PlayerId();
        player = new HumanPlayer(
            new Player.Builder()
                .withPlayerId(playerId)
                .withDisplay(display)
                .withGameResultDecider(gameResultDecider),
            userInput);

        board = new Board();
    }

    private void expectWinner(Optional<PlayerId> value) {
        when(gameResultDecider.winnerIs(board)).thenReturn(value);
    }

    private Optional<PlayerId> noWinner() {
        return Optional.empty();
    }

    private void makeBoardOccupiedAtSecondPosition() {
        board.setPlayerAtPosition(playerId, new PositionOnBoard(0, 1));
    }

    private void fillBoardToFullExceptFirstOne() {
        for (int idx = 0; idx < board.getDimension(); idx++) {
            for (int idy = 0; idy < board.getDimension(); idy++) {
                board.setPlayerAtPosition(playerId, new PositionOnBoard(idx, idy));
            }
        }

        board.setPlayerAtPosition(null, new PositionOnBoard(0, 0));
    }

    @Test
    public void invalidMoveIsFallowedByValidOne() {

        expectUserInputs("0", "1");
        expectWinner(noWinner());

        player.makesMove(board);

        verify(display, times(2)).displayInstructions(playerId);
        verify(display).displayInvalidMove();
        verify(display).displayBoard(board);
    }

    @Test
    public void invalidInputIsFallowedByValidOne() {

        expectUserInputs("p", "1");
        expectWinner(noWinner());

        player.makesMove(board);
        verify(display, times(2)).displayInstructions(playerId);
        verify(display).displayInvalidMove();
        verify(display).displayBoard(board);
    }

    @Test
    public void alreadyOccupiedFieldIsFallowedByValidOne() {

        expectUserInputs("2", "3");
        expectWinner(noWinner());

        makeBoardOccupiedAtSecondPosition();

        player.makesMove(board);
        verify(display, times(2)).displayInstructions(playerId);
        verify(display).displayFieldIsOccupied();
        verify(display).displayBoard(board);
    }

    @Test
    public void alreadyOccupiedFieldIsFallowedByInvalidOneAndValidOne() {

        expectUserInputs("2", "p", "3");
        expectWinner(noWinner());

        makeBoardOccupiedAtSecondPosition();

        player.makesMove(board);
        verify(display, times(3)).displayInstructions(playerId);
        verify(display).displayFieldIsOccupied();
        verify(display).displayInvalidMove();
        verify(display).displayBoard(board);
    }

    @Test
    public void validMove(){

        expectUserInputs("5");
        expectWinner(noWinner());

        Board resultBoard = player.makesMove(board).get();

        assertEquals(resultBoard.getPlayerAtPosition(new PositionOnBoard(1, 1)), playerId);

        verify(display).displayInstructions(playerId);
        verify(display).displayBoard(board);
    }

    @Test public void winningGame() {
        expectUserInputs("1");
        expectWinner(Optional.of(playerId));

        noMoreMovesInGame();

        verify(display).displayInstructions(playerId);
        verify(display).displayBoard(board);
        verify(display).displayPlayerWon(playerId);
    }

    @Test public void loosingGame() {
        expectUserInputs("1");
        PlayerId otherPlayerId = new PlayerId();
        expectWinner(Optional.of(otherPlayerId));

        noMoreMovesInGame();

        verify(display).displayInstructions(playerId);
        verify(display).displayBoard(board);
        verify(display).displayPlayerWon(otherPlayerId);
    }

    @Test public void tieGame() {
        expectUserInputs("1");
        expectWinner(noWinner());

        fillBoardToFullExceptFirstOne();

        noMoreMovesInGame();

        verify(display).displayInstructions(playerId);
        verify(display).displayBoard(board);
        verify(display).displayTie();
    }
}
