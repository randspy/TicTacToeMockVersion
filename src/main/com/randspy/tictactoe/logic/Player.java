package com.randspy.tictactoe.logic;

import java.util.Optional;

public abstract class Player {
    private PlayerId playerId;

    protected Player(PlayerId playerId) {
        this.playerId = playerId;
    }

    public abstract Optional<Board> makesMove(Board board);

    public PlayerId getId(){
        return playerId;
    }
}
