package com.randspy.tictactoe;

import java.util.Optional;

public interface Player {
    Optional<Board> makesMove();
}
