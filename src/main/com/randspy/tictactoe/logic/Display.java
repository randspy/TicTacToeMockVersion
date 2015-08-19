package com.randspy.tictactoe.logic;

import com.randspy.tictactoe.logic.Board;

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

    public void displayBoard(Board board) {

    }

    public void displayPlayerWon(PlayerId id) {
        render.send(String.format("%s won", mapper.getName(id)));
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
}
