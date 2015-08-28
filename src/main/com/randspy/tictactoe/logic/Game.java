package com.randspy.tictactoe.logic;

public class Game {
    private final PlayersCircularList playersCircularList;
    private GameState gameState;

    public Game(PlayersCircularList playersCircularList, Board initialBoard) {
        this.playersCircularList = playersCircularList;
        this.gameState = new GameState(initialBoard, GameProgress.InProgress);
    }

    public void play() {
        while (gameState.isInProgress()) {
            gameState = makeMove(gameState.board());
        }
    }

    private GameState makeMove(Board board) {
        return playersCircularList.next().makesMove(board);
    }
}
