package com.randspy.test.tictactoe;

import com.randspy.tictactoe.Board;
import com.randspy.tictactoe.Game;
import com.randspy.tictactoe.Player;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;

public class BuildingGameObjectTest {

    @Test public void buildGame() {
        assertNotNull(new Game.Builder()
                .withPlayer(new Player() {
                    public Optional<Board> makesMove(Board board) {
                        return null;
                    }
                })
                .withBoard(new Board())
                .build());

    }
}
