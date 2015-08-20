package com.randspy.test.tictactoe.logic;

import com.randspy.tictactoe.logic.PlayerId;
import com.randspy.tictactoe.logic.PlayerToDisplayMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ConvertPlayerToDisplayedRepresentationTest {
    private PlayerId playerId;
    private PlayerToDisplayMapper mapping;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        mapping = new PlayerToDisplayMapper();
        playerId = new PlayerId();
    }


    @Test
    public void noMappingToCharacter() {
        assertFalse(mapping.getCharacter(playerId).isPresent());
    }

    @Test
    public void mappingToCharacter() {
        mapping.map(playerId, "x");
        assertEquals("x", mapping.getCharacter(playerId).get());
    }


    @Test
    public void noMappingToName() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Missing mapping from player to name");
        mapping.getName(playerId);
    }

    @Test
    public void mappingToName() {
        String name = "name";
        mapping.mapName(playerId, name);
        assertEquals(name, mapping.getName(playerId));
    }
}
