package com.randspy.tictactoe;

import java.util.Optional;

public class Game {
    private Players players;
    private Board board;

    public Game(Players players, Board board) {
        this.players = players;
        this.board = board;
    }

    public void play() {

        Optional<Board> resultBoardAfterMove = makeMove();

        while (resultBoardAfterMove.isPresent()) {
            board = resultBoardAfterMove.get();
            resultBoardAfterMove = makeMove();
        }
    }

    private Optional<Board> makeMove() {
        return players.next().makesMove(board);
    }

}
