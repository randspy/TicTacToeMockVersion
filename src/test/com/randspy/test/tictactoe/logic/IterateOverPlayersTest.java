package com.randspy.test.tictactoe.logic;

import com.randspy.tictactoe.logic.Board;
import com.randspy.tictactoe.logic.Player;
import com.randspy.tictactoe.logic.PlayerId;
import com.randspy.tictactoe.logic.Players;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class IterateOverPlayersTest {

    public class StubPlayer extends Player {
        protected StubPlayer(Player.Builder builder) {
            super(builder);
        }

        @Override
        public Optional<Board> makesMove(Board board) {
            return null;
        }
    };

    @Test
    public void getNextPlayer() {

        Player player = new StubPlayer(new Player.Builder().withPlayerId(new PlayerId()));
        Players players = new Players(player);

        assertEquals(player, players.next());
    }

    @Test
    public void getMoreThanOnePlayer() {

        Player playerOne = new StubPlayer(new Player.Builder().withPlayerId(new PlayerId()));
        Player playerTwo = new StubPlayer(new Player.Builder().withPlayerId(new PlayerId()));
        Players players = new Players(playerOne, playerTwo);

        assertEquals(playerOne, players.next());
        assertEquals(playerTwo, players.next());
    }

    @Test
    public void whenListOfPlayersIsWrappedGetFirstPlayerTwice() {

        Player playerOne = new StubPlayer(new Player.Builder().withPlayerId(new PlayerId()));
        Player playerTwo = new StubPlayer(new Player.Builder().withPlayerId(new PlayerId()));
        Players players = new Players(playerOne, playerTwo);

        assertEquals(playerOne, players.next());
        assertEquals(playerTwo, players.next());
        assertEquals(playerOne, players.next());
        assertEquals(playerTwo, players.next());
        assertEquals(playerOne, players.next());
        assertEquals(playerTwo, players.next());
        assertEquals(playerOne, players.next());
    }
}
