package com.randspy.tictactoe;

import java.util.Optional;

public abstract class Player {
    private PlayerId playerId = new PlayerId();

    public abstract Optional<Board> makesMove(Board board);

    public PlayerId getId(){
        return playerId;
    }
}
