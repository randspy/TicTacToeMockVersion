package com.randspy.test.tictactoe.logic;

import com.randspy.tictactoe.logic.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinMaxTest {

    private MinMax ai;
    private PlayerId opponent;
    private Board board;
    private PlayerId playerId;

    private void expectMove(PositionOnBoard opponentsMove, int expectedRow, int expectedColumn) {
        board.setPlayerAtPosition(opponent, opponentsMove);
        PositionOnBoard positionOnBoard = ai.move(board);
        board.setPlayerAtPosition(playerId, positionOnBoard);
        assertEquals(expectedRow, positionOnBoard.getRow());
        assertEquals(expectedColumn, positionOnBoard.getColumn());
    }

    @Before
    public void setUp() throws Exception {
        playerId = new PlayerId();
        ai = new MinMax(playerId);
        opponent = new PlayerId();
        board = new Board();
    }

    @Test
    public void computerWinningGame() {
        expectMove(new PositionOnBoard(1, 1), 0, 0);
        expectMove(new PositionOnBoard(2, 2), 0, 2);
        expectMove(new PositionOnBoard(2, 1), 0, 1);
    }


    @Test
    public void tieGame() {
        expectMove(new PositionOnBoard(1, 1), 0, 0);
        expectMove(new PositionOnBoard(0, 2), 2, 0);
        expectMove(new PositionOnBoard(1, 0), 1, 2);
        expectMove(new PositionOnBoard(2, 1), 0, 1);
    }

}
