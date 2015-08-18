package com.randspy.test.tictactoe;

import com.randspy.tictactoe.Board;
import com.randspy.tictactoe.Player;

import java.util.Optional;

public class StubPlayer extends Player {
    @Override
    public Optional<Board> makesMove(Board board) {
        return null;
    }
};