package com.randspy.tictactoe.logic;

import java.util.HashMap;
import java.util.Map;

public class PlayerToDisplayMapper {
    private Map<PlayerId, String> characterMapping = new HashMap<>();
    private Map<PlayerId, String> nameMapping = new HashMap<>();

    public void mapName(PlayerId playerId, String name) {
        nameMapping.put(playerId, name);
    }

    public String getName(PlayerId playerId) {
        String name = nameMapping.get(playerId);
        if (name == null) {
            //We assume that if we have a missing mapping something went horrible wrong when wiring objects.
            //We should not recover from that.
            throw new RuntimeException("Missing mapping from player to name");
        }
        return name;
    }

    public void mapCharacter(PlayerId playerId, String character) {
        characterMapping.put(playerId, character);
    }

    public String getCharacter(PlayerId player) {
        String character = characterMapping.get(player);
        if (character == null) {
            //We assume that if we have a missing mapping something went horrible wrong when wiring objects.
            //We should not recover from that.
            throw new RuntimeException("Missing mapping from player to character");
        }
        return character;
    }

}
