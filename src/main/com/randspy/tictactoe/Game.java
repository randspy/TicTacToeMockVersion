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

        while(true) {

            Optional<Board> optionalBoard = players.next().makesMove(board);
            if (optionalBoard.isPresent()) {
                board = optionalBoard.get();
            }
            else {
                break;
            }
        }
    }
}
