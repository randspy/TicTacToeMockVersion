package com.randspy.tictactoe;

public class Game {
    private Player player;
    private Board board;

    public static class Builder {

        private Player player;
        private Board board;

        public Builder withPlayer(Player player) {
            this.player = player;
            return this;
        }

        public Builder withBoard(Board board) {
            this.board = board;
            return this;
        }

        public Game build() {
            return new Game(this);
        }

    }

    public Game(Builder builder) {
        this.player = builder.player;
        this.board = builder.board;
    }

    public void play() {
        while(player.makesMove(board).isPresent()) {}
    }
}
