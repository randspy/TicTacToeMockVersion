package com.randspy.tictactoe.logic;

import com.randspy.tictactoe.logic.Board;

public class Display {
    private OutputRender render;

    public Display(OutputRender render) {

        this.render = render;
    }

    public void displayInvalidMove() {
        render.send("Illegal input.\n");

    }

    public void displayBoard(Board board) {

    }

    public void displayPlayerWon(PlayerId id) {

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
