package com.randspy.tictactoe.logic;

import java.util.Optional;

public class Display {
    private OutputRender render;
    private PlayerToDisplayMapper mapper;

    public Display(OutputRender render, PlayerToDisplayMapper mapper) {

        this.render = render;
        this.mapper = mapper;
    }

    public void displayInvalidMove() {
        render.send("Illegal input.\n");
    }

    public void displayPlayerWon(PlayerId id) {
        render.send(String.format("%s won\n", mapper.getName(id)));
    }

    public void displayTie() {
        render.send("There was a tie.\n");
    }

    public void displayInstructions() {
        render.send("Make move (from 1-9) : ");
    }

    public void displayFieldIsOccupied() {
        render.send("Already occupied field.\n");
    }

    public void displayBoard(Board board) {

        render.send(
                printHorizontalSeparator() +
                printRow(board, 0) +
                printHorizontalSeparator() +
                printRow(board, 1) +
                printHorizontalSeparator() +
                printRow(board, 2) +
                printHorizontalSeparator());
    }

    private String printHorizontalSeparator() {
        return "-------\n";
    }

    private String printRow(Board board, int row) {
        final String rowSeparator = "|";
        String printedRow = rowSeparator;

        for (int idx = 0; idx < board.getDimension(); idx++) {
            PlayerId playerAtPosition = board.getPlayerAtPosition(new PositionOnBoard(row, idx));

            final String emptyField = " ";
            final String gameFiled = playerAtPosition != null ? mapper.getCharacter(playerAtPosition) : emptyField;

            printedRow += gameFiled + rowSeparator;
        }
        return printedRow + "\n";
    }
}
