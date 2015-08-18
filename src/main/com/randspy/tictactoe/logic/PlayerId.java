package com.randspy.tictactoe.logic;

import java.util.UUID;

public class PlayerId {
    private final UUID uuid;

    public PlayerId() {
        this.uuid = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerId)) return false;

        PlayerId playerId = (PlayerId) o;

        if (uuid != null ? !uuid.equals(playerId.uuid) : playerId.uuid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return uuid != null ? uuid.hashCode() : 0;
    }
}
