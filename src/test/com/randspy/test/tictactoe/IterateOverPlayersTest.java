package com.randspy.test.tictactoe;

import com.randspy.tictactoe.Board;
import com.randspy.tictactoe.Player;
import com.randspy.tictactoe.Players;
import org.junit.Test;

import javax.rmi.CORBA.Stub;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class IterateOverPlayersTest {
    @Test
    public void getNextPlayer() {

        Player player = new StubPlayer();
        Players players = new Players(player);

        assertEquals(player, players.next());
    }

    @Test
    public void getMoreThanOnePlayer() {

        Player playerOne = new StubPlayer();
        Player playerTwo = new StubPlayer();
        Players players = new Players(playerOne, playerTwo);

        assertEquals(playerOne, players.next());
        assertEquals(playerTwo, players.next());
    }

    @Test
    public void whenListOfPlayersIsWrappedGetFirstPlayerTwice() {

        Player playerOne = new StubPlayer();
        Player playerTwo = new StubPlayer();
        Players players = new Players(playerOne, playerTwo);

        assertEquals(playerOne, players.next());
        assertEquals(playerTwo, players.next());
        assertEquals(playerOne, players.next());
    }
}
