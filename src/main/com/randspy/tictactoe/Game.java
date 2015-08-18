package com.randspy.tictactoe;

public class Game {
    private Player player;

    public Game(Player player) {

        this.player = player;
    }

    public void play() {
        player.makesMove();
    }
}
