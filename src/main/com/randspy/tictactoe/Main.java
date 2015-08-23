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
        PlayerId playerOneId = new PlayerId();
        Player playerOne = new HumanPlayer.Builder()
                        .withPlayerId(playerOneId)
                        .withDisplay(display)
                        .withGameResultDecider(new GameResult())
                        .withUserInput(new UserInput())
                        .build();

        PlayerId playerTwoId = new PlayerId();
        Player playerTwo = new HumanPlayer.Builder()
                        .withPlayerId(playerTwoId)
                        .withDisplay(display)
                        .withGameResultDecider(new GameResult())
                        .withUserInput(new UserInput())
                        .build();

        mapper.mapName(playerOneId, "Player one");
        mapper.mapCharacter(playerOneId, "x");

        mapper.mapName(playerTwoId, "Player two");
        mapper.mapCharacter(playerTwoId, "o");


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

        Player playerOne = new HumanPlayer.Builder()
                        .withPlayerId(humanPlayerId)
                        .withDisplay(display)
                        .withGameResultDecider(new GameResult())
                        .withUserInput(new UserInput())
                        .build();

        Player playerTwo = new ComputerPlayer(
                computerPlayerId, display, new GameResult(), new MinMax(computerPlayerId));

        Players players = new Players(playerOne, playerTwo);
        return new Game(players, new Board());
    }
}
