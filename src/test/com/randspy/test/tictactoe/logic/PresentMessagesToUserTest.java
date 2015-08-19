package com.randspy.test.tictactoe.logic;

import com.randspy.tictactoe.logic.Display;
import com.randspy.tictactoe.logic.OutputRender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PresentMessagesToUserTest {

    @Mock
    private OutputRender render;
    private Display display;

    @Before
    public void setUp() throws Exception {
        display = new Display(render);
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
}
