package com.randspy.tictactoe.logic;

public class Display {
    private OutputRender render;
    private PlayerToDisplayMapper mapper;

    public Display(OutputRender render, PlayerToDisplayMapper mapper) {

        this.render = render;
        this.mapper = mapper;
    }

    public void invalidMove() {
        render.send("Illegal input.\n");
    }

    public void playerWon(PlayerId playerId) {
        render.send(String.format("%s won\n", mapper.getName(playerId)));
    }

    public void tie() {
        render.send("There was a tie.\n");
    }

    public void instructions(PlayerId playerId) {
        render.send(String.format("%s makes move (from 1-9) : ", mapper.getName(playerId)));
    }

    public void fieldIsOccupied() {
        render.send("Already occupied field.\n");
    }

    public void board(Board board) {

        render.send(
                horizontalSeparator() +
                row(board, 0) +
                horizontalSeparator() +
                row(board, 1) +
                horizontalSeparator() +
                row(board, 2) +
                horizontalSeparator());
    }

    private String horizontalSeparator() {
        return "-------\n";
    }

    private String row(Board board, int row) {
        final String rowSeparator = "|";
        String printedRow = rowSeparator;

        for (int idx = 0; idx < board.getDimension(); idx++) {
            PlayerId playerAtPosition = board.getPlayerAtPosition(new PositionOnBoard(row, idx));

            final String emptyField = " ";
            final String gameFiled = playerAtPosition != null ?
                    mapper.getCharacter(playerAtPosition) : emptyField;

            printedRow += gameFiled + rowSeparator;
        }
        return printedRow + "\n";
    }
}
