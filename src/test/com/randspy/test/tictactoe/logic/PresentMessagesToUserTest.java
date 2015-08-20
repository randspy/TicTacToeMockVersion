package com.randspy.test.tictactoe.logic;

import com.randspy.tictactoe.logic.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PresentMessagesToUserTest {

    @Mock
    private OutputRender render;
    private Display display;
    @Mock
    private PlayerToDisplayMapper mapper;

    private Board generateBoard(PlayerId xPlayerId, PlayerId yPlayerId) {
        Board board = new Board();
        int numberOfFields = board.getDimension() * board.getDimension();
        for (int idx = 1; idx < numberOfFields; idx++) {

            boolean isEven = idx % 2 == 0;
            int row = idx / board.getDimension();
            int column = idx % board.getDimension();
            board.setPlayerAtPosition(isEven ? xPlayerId :yPlayerId, new PositionOnBoard(row, column));
        }
        return board;
    }

    @Before
    public void setUp() throws Exception {
        display = new Display(render, mapper);
    }

    @Test
    public void displayInstructions() {
        display.displayInstructions();
        verify(render).send("Make move (from 1-9) : ");
    }

    @Test
    public void displayTie() {
        display.displayTie();
        verify(render).send("There was a tie.\n");
    }

    @Test
    public void displayInvalidMove() {
        display.displayInvalidMove();
        verify(render).send("Illegal input.\n");
    }

    @Test
    public void displayFieldIsOccupied() {
        display.displayFieldIsOccupied();
        verify(render).send("Already occupied field.\n");
    }

    @Test
    public void displayPlayerWon() {
        PlayerId playerId = new PlayerId();
        String name = "Human player";

        when(mapper.getName(playerId)).thenReturn(name);

        display.displayPlayerWon(playerId);
        verify(render).send(String.format("%s won", name));
    }

    @Test
    public void displayBoard() {

        PlayerId xPlayerId = new PlayerId();
        PlayerId yPlayerId = new PlayerId();

        when(mapper.getCharacter(xPlayerId)).thenReturn("x");
        when(mapper.getCharacter(yPlayerId)).thenReturn("o");

        Board board = generateBoard(xPlayerId, yPlayerId);

        display.displayBoard(board);
        verify(render).send(
                "-------\n" +
                "| |o|x|\n" +
                "-------\n" +
                "|o|x|o|\n" +
                "-------\n" +
                "|x|o|x|\n" +
                "-------\n");
    }
}
