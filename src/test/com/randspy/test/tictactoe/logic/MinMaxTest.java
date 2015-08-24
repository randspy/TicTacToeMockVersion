package com.randspy.test.tictactoe.logic;

import com.randspy.tictactoe.logic.Board;
import com.randspy.tictactoe.logic.MinMax;
import com.randspy.tictactoe.logic.PlayerId;
import com.randspy.tictactoe.logic.PositionOnBoard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinMaxTest {

    private MinMax ai;
    private PlayerId aiPlayerId;
    private PlayerId opponent;
    private Board board;

    private void expectMove(int expectedRow, int expectedColumn, PositionOnBoard opponentsMove) {
        board.setPlayerAtPosition(opponent, opponentsMove);
        PositionOnBoard positionOnBoard = ai.move(board);
        board.setPlayerAtPosition(aiPlayerId, positionOnBoard);
        assertEquals(expectedRow, positionOnBoard.getRow());
        assertEquals(expectedColumn, positionOnBoard.getColumn());
    }

    private PositionOnBoard forOpponentsMove(int row, int column) {
        return new PositionOnBoard(row, column);
    }

    @Before
    public void setUp() throws Exception {
        aiPlayerId = new PlayerId();
        ai = new MinMax(aiPlayerId);
        opponent = new PlayerId();
        board = new Board();
    }

    @Test
    public void computerWinningGame() {
        expectMove(0, 0, forOpponentsMove(1, 1));
        expectMove(0, 2, forOpponentsMove(2, 2));
        expectMove(0, 1, forOpponentsMove(2, 1));
    }


    @Test
    public void tieGame() {
        expectMove(0, 0, forOpponentsMove(1, 1));
        expectMove(2, 0, forOpponentsMove(0, 2));
        expectMove(1, 2, forOpponentsMove(1, 0));
        expectMove(0, 1, forOpponentsMove(2, 1));
    }

}
