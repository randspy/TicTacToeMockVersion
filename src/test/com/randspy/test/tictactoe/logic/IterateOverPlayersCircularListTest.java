package com.randspy.test.tictactoe.logic;

import com.randspy.tictactoe.logic.Board;
import com.randspy.tictactoe.logic.Player;
import com.randspy.tictactoe.logic.PlayerId;
import com.randspy.tictactoe.logic.PlayersCircularList;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class IterateOverPlayersCircularListTest {

    public class StubPlayer extends Player {
        protected StubPlayer(Player.Builder builder) {
            super(builder);
        }

        @Override
        public Optional<Board> makesMove(Board board) {
            return null;
        }
    }

    private Player createPlayer() {
        return new StubPlayer(
                new Player.Builder().withPlayerId(new PlayerId()));
    }

    @Test
    public void getNextPlayer() {

        Player player = createPlayer();
        PlayersCircularList playersCircularList = new PlayersCircularList(player);

        assertEquals(player, playersCircularList.next());
    }

    @Test
    public void getMoreThanOnePlayer() {

        Player playerOne = createPlayer();
        Player playerTwo = createPlayer();
        PlayersCircularList playersCircularList = new PlayersCircularList(playerOne, playerTwo);

        assertEquals(playerOne, playersCircularList.next());
        assertEquals(playerTwo, playersCircularList.next());
    }

    @Test
    public void whenListOfPlayersIsWrappedGetFirstPlayerTwice() {

        Player playerOne = createPlayer();
        Player playerTwo = createPlayer();
        PlayersCircularList playersCircularList = new PlayersCircularList(playerOne, playerTwo);

        assertEquals(playerOne, playersCircularList.next());
        assertEquals(playerTwo, playersCircularList.next());
        assertEquals(playerOne, playersCircularList.next());
        assertEquals(playerTwo, playersCircularList.next());
        assertEquals(playerOne, playersCircularList.next());
        assertEquals(playerTwo, playersCircularList.next());
        assertEquals(playerOne, playersCircularList.next());
    }
}
