package com.randspy.test.tictactoe.logic;

import com.randspy.tictactoe.logic.Board;
import com.randspy.tictactoe.logic.PlayerId;
import com.randspy.tictactoe.logic.PositionOnBoard;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class BoardContentManipulationsTest {

    private PlayerId playerId;
    private Board board;

    private void genFullBoard() {
        for (int idx = 0; idx < board.getDimension(); idx++) {
            for (int idy = 0; idy < board.getDimension(); idy++) {
                board.setPlayerAtPosition(playerId, new PositionOnBoard(idx, idy));
            }
        }
    }

    @Before
    public void setUp() throws Exception {
        board = new Board();
        playerId = new PlayerId();
    }

    @Test
    public void whenFieldIsSetWeCanGetIt() {

        PositionOnBoard position = new PositionOnBoard(0, 0);
        board.setPlayerAtPosition(playerId, position);
        assertEquals(playerId, board.getPlayerAtPosition(position));
    }

    @Test
    public void whenNotAllFieldsOccupiedThenBoardIsNotFull() {
        assertFalse(board.isFull());
    }

    @Test
    public void whenAllFieldsOccupiedThenBoardIsFull() {

        genFullBoard();

        assertTrue(board.isFull());
    }

    @Test
    public void getColumn() {

        PositionOnBoard position = new PositionOnBoard(0, 0);

        board.setPlayerAtPosition(playerId, position);

        assertArrayEquals(new PlayerId[]{playerId, null, null},
                board.getPlayersAtColumn(0));
    }

    @Test
    public void getRow() {

        PositionOnBoard position = new PositionOnBoard(0, 0);

        board.setPlayerAtPosition(playerId, position);

        assertArrayEquals(new PlayerId[]{playerId, null, null},
                board.getPlayersAtRow(0));
    }

    @Test
    public void getDiagonalFromLeftToRight() {

        PositionOnBoard centralPosition = new PositionOnBoard(1, 1);
        PositionOnBoard leftTopPosition = new PositionOnBoard(0, 0);

        board.setPlayerAtPosition(playerId, centralPosition);
        board.setPlayerAtPosition(playerId, leftTopPosition);

        assertArrayEquals(new PlayerId[]{playerId, playerId, null},
                board.getPlayersAtDiagonalFromLeftToRight());
    }


    @Test
    public void getDiagonalFromRightToLeft() {

        PositionOnBoard centralPosition = new PositionOnBoard(1, 1);
        PositionOnBoard rightTopPosition = new PositionOnBoard(0, 2);

        board.setPlayerAtPosition(playerId, centralPosition);
        board.setPlayerAtPosition(playerId, rightTopPosition);

        assertArrayEquals(new PlayerId[]{playerId, playerId, null},
                board.getPlayersAtDiagonalFromRightToLeft());
    }

    @Test
    public void getPresentPlayers() {

        PositionOnBoard centralPosition = new PositionOnBoard(1, 1);
        board.setPlayerAtPosition(playerId, centralPosition);

        assertTrue(board.getPresentPlayers().contains(playerId));
    }

    @Test
    public void getEmptyFields() {

        genFullBoard();

        board.setPlayerAtPosition(null, new PositionOnBoard(1, 1));
        List<PositionOnBoard> emptyFields = board.getEmptyFields();

        assertEquals(1, emptyFields.size());
        assertEquals(1, emptyFields.get(0).getRow());
        assertEquals(1, emptyFields.get(0).getRow());
    }
}