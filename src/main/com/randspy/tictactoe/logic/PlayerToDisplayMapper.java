package com.randspy.tictactoe.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PlayerToDisplayMapper {
    Map<PlayerId, String> mapping = new HashMap<>();
    Map<PlayerId, String> nameMapping = new HashMap<>();

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

    public void map(PlayerId playerId, String character) {
        mapping.put(playerId, character);
    }

    public Optional<String> getCharacter(PlayerId player) {
        String character = mapping.get(player);
        return character == null ? Optional.ofNullable(null) : Optional.ofNullable(character);
    }

}
