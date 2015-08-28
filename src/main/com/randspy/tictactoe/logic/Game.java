package com.randspy.tictactoe.logic;

import java.util.Optional;

public class Game {
    private PlayersCircularList playersCircularList;
    private Board board;

    public Game(PlayersCircularList playersCircularList, Board board) {
        this.playersCircularList = playersCircularList;
        this.board = board;
    }

    public void play() {

        Optional<Board> resultBoardAfterMove = makeMove().board();

        while (resultBoardAfterMove.isPresent()) {
            board = resultBoardAfterMove.get();
            resultBoardAfterMove = makeMove().board();
        }
    }

    private GameState makeMove() {
        return playersCircularList.next().makesMove(board);
    }

}
