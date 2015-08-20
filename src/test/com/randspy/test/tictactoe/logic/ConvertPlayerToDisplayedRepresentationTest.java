package com.randspy.test.tictactoe.logic;

import com.randspy.tictactoe.logic.PlayerId;
import com.randspy.tictactoe.logic.PlayerToDisplayMapper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ConvertPlayerToDisplayedRepresentationTest {
    private PlayerId playerId;
    private PlayerToDisplayMapper mapping;

    @Before
    public void setUp() throws Exception {
        mapping = new PlayerToDisplayMapper();
        playerId = new PlayerId();
    }


    @Test
    public void whenNoMappingExistsReturnNothing() {
        assertFalse(mapping.getCharacter(playerId).isPresent());
    }

    @Test
    public void whenMappingExistsReturnCharacter() {
        mapping.map(playerId, "x");
        assertEquals("x", mapping.getCharacter(playerId).get());
    }
}
