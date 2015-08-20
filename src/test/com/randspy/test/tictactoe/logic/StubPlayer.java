package com.randspy.test.tictactoe.logic;

import com.randspy.tictactoe.logic.Board;
import com.randspy.tictactoe.logic.Player;
import com.randspy.tictactoe.logic.PlayerId;

import java.util.Optional;

public class StubPlayer extends Player {
    protected StubPlayer(PlayerId playerId) {
        super(playerId);
    }

    @Override
    public Optional<Board> makesMove(Board board) {
        return null;
    }
};