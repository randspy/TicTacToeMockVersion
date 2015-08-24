package com.randspy.tictactoe.logic;

import java.util.Optional;

public abstract class Player {
    protected PlayerId playerId;
    protected Display display;
    protected GameResult gameResult;

    protected Player(Builder builder) {

        this.playerId = builder.playerId;
        this.display = builder.display;
        this.gameResult = builder.gameResult;
    }

    public abstract Optional<Board> makesMove(Board board);

    public PlayerId getId(){
        return playerId;
    }

    public static class Builder{

        private PlayerId playerId;
        private Display display;
        private GameResult gameResult;

        public Builder withPlayerId(PlayerId playerId) {
            this.playerId = playerId;
            return this;
        }

        public Builder withDisplay(Display display) {
            this.display = display;
            return this;
        }

        public Builder withGameResultDecider(GameResult gameResult) {
            this.gameResult = gameResult;
            return this;
        }
    }
}
