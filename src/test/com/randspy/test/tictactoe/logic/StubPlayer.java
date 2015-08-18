package com.randspy.test.tictactoe.logic;

import com.randspy.tictactoe.logic.Board;
import com.randspy.tictactoe.logic.Player;

import java.util.Optional;

public class StubPlayer extends Player {
    @Override
    public Optional<Board> makesMove(Board board) {
        return null;
    }
};