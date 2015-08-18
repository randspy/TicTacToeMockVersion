package com.randspy.tictactoe.logic;

import java.util.*;

public class Board {
    private static final int DIMENSION = 3;

    private PlayerId[][] boardElements = new PlayerId[DIMENSION][DIMENSION];
    public int getDimension() {
        return boardElements.length;
    }

    public void setPlayerAtPosition(PlayerId playerId, PositionOnBoard position) {
        boardElements[position.getRow()][position.getColumn()] = playerId;
    }

    public boolean isFull() {

        for(PlayerId[] row : boardElements) {
            for (PlayerId elem : row) {
                if (elem == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public PlayerId getPlayerAtPosition(PositionOnBoard position) {
        return boardElements[position.getRow()][position.getColumn()];
    }

    public PlayerId[] getPlayersAtRow(int idx) {
        return boardElements[idx];
    }

    public PlayerId[] getPlayersAtColumn(int idx) {

        return Arrays.stream(boardElements).map(row -> row[idx]).toArray(PlayerId[]::new);
    }

    public PlayerId[] getPlayersAtDiagonalFromLeftToRight() {
        PlayerId [] diagonal = new PlayerId[boardElements.length];

        for (int idx = 0; idx < boardElements.length; idx++) {
            diagonal[idx] = boardElements[idx][idx];
        }
        return diagonal;
    }

    public PlayerId[] getPlayersAtDiagonalFromRightToLeft() {
        PlayerId [] diagonal = new PlayerId[boardElements.length];

        int idy = boardElements.length - 1;
        for (int idx = 0; idx < boardElements.length; idx++, idy--) {
            diagonal[idx] = boardElements[idx][idy];
        }
        return diagonal;
    }

    public List<PositionOnBoard> getEmptyFields() {

        List<PositionOnBoard> positions = new LinkedList<>();

        for (int idx = 0; idx < boardElements.length; idx++) {
            for (int idy = 0; idy < boardElements[idx].length; idy++) {
                if (boardElements[idx][idy] == null) {
                    positions.add(new PositionOnBoard(idx, idy));
                }
            }
        }
        return positions;
    }

    public boolean isPositionOccupied(PositionOnBoard position) {
        return boardElements[position.getRow()][position.getColumn()] != null;
    }

    public Set<PlayerId> getPresentPlayers() {

        Set<PlayerId> players = new HashSet<>();

        for(PlayerId[] row : boardElements) {
            for (PlayerId elem : row) {
                players.add(elem);
            }
        }

        return players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        if (boardElements.length != board.boardElements.length) return false;

        for (int idx = 0; idx < boardElements.length; idx++) {
            if (!Arrays.equals(boardElements[idx], board.boardElements[idx])) return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return boardElements != null ? Arrays.hashCode(boardElements) : 0;
    }
}
