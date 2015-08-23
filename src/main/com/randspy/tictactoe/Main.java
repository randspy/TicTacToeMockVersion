package com.randspy.tictactoe;

import com.randspy.tictactoe.logic.*;

public class Main {
    public static void main(String[] args) {

//        createHumanToHumanGame().play();
        createHumanToComputerGame().play();
    }

    private static Game createHumanToHumanGame() {
        PlayerToDisplayMapper mapper = new PlayerToDisplayMapper();

        Display display = new Display(new OutputRender(), mapper);
        Player playerOne = new HumanPlayer(new PlayerId(), display, new GameResult(), new UserInput());
        Player playerTwo = new HumanPlayer(new PlayerId(), display, new GameResult(), new UserInput());

        mapper.mapName(playerOne.getId(), "Player one");
        mapper.mapCharacter(playerOne.getId(), "x");

        mapper.mapName(playerTwo.getId(), "Player two");
        mapper.mapCharacter(playerTwo.getId(), "o");


        Players players = new Players(playerOne, playerTwo);
        return new Game(players, new Board());
    }

    private static Game createHumanToComputerGame() {

        PlayerId humanPlayerId = new PlayerId();
        PlayerId computerPlayerId = new PlayerId();

        PlayerToDisplayMapper mapper = new PlayerToDisplayMapper();
        mapper.mapName(humanPlayerId, "Human");
        mapper.mapCharacter(humanPlayerId, "x");

        mapper.mapName(computerPlayerId, "Computer");
        mapper.mapCharacter(computerPlayerId, "o");

        Display display = new Display(new OutputRender(), mapper);

        Player playerOne = new HumanPlayer(
                humanPlayerId, display, new GameResult(), new UserInput());

        Player playerTwo = new ComputerPlayer(
                computerPlayerId, display, new GameResult(), new MinMax(computerPlayerId));

        Players players = new Players(playerOne, playerTwo);
        return new Game(players, new Board());
    }
}
