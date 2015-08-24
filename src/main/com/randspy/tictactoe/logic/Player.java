package com.randspy.tictactoe.logic;

import java.util.Optional;

public abstract class Player {
    protected PlayerId playerId;
    protected Display display;
    protected GameResultDecider gameResultDecider;

    protected Player(Builder builder) {

        this.playerId = builder.playerId;
        this.display = builder.display;
        this.gameResultDecider = builder.gameResultDecider;
    }

    public abstract Optional<Board> makesMove(Board board);

    public static class Builder{

        private PlayerId playerId;
        private Display display;
        private GameResultDecider gameResultDecider;

        public Builder withPlayerId(PlayerId playerId) {
            this.playerId = playerId;
            return this;
        }

        public Builder withDisplay(Display display) {
            this.display = display;
            return this;
        }

        public Builder withGameResultDecider(GameResultDecider gameResultDecider) {
            this.gameResultDecider = gameResultDecider;
            return this;
        }
    }
}
