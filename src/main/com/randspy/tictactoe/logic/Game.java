package com.randspy.tictactoe.logic;

public class Game {
    private PlayersCircularList playersCircularList;
    private Board board;

    public Game(PlayersCircularList playersCircularList, Board board) {
        this.playersCircularList = playersCircularList;
        this.board = board;
    }

    public void play() {
        GameState newGameState = makeMove();

        while (newGameState.isInProgress()) {
            board = newGameState.board();
            newGameState = makeMove();
        }
    }

    private GameState makeMove() {
        return playersCircularList.next().makesMove(board);
    }

}
