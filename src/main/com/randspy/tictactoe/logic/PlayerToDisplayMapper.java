package com.randspy.tictactoe.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PlayerToDisplayMapper {
    Map<PlayerId, String> mapping = new HashMap<>();

    public String getName(PlayerId playerId) {
        return null;
    }

    public void map(PlayerId playerId, String character) {
        mapping.put(playerId, character);
    }

    public Optional<String> getCharacter(PlayerId player) {
        String character = mapping.get(player);
        return character == null ? Optional.ofNullable(null) : Optional.ofNullable(character);
    }

}
