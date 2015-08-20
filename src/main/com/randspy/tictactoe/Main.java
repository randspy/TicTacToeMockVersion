package com.randspy.tictactoe;

import com.randspy.tictactoe.logic.*;

public class Main {
    public static void main(String[] args) {

        createHumanToHumanGame().play();
    }

    private static Game createHumanToHumanGame() {
        PlayerToDisplayMapper mapper = new PlayerToDisplayMapper();

        Display display = new Display(new OutputRender(), mapper);
        Player playerOne = new HumanPlayer(new PlayerId(), display, new UserInput(), new GameResult());
        Player playerTwo = new HumanPlayer(new PlayerId(), display, new UserInput(), new GameResult());

        mapper.mapName(playerOne.getId(), "Player one");
        mapper.mapCharacter(playerOne.getId(), "x");

        mapper.mapName(playerTwo.getId(), "Player two");
        mapper.mapCharacter(playerTwo.getId(), "o");


        Players players = new Players(playerOne, playerTwo);
        return new Game(players, new Board());
    }
}
