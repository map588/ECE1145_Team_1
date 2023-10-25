package hotciv.standard;

import hotciv.framework.*;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class epsilonCiv_tests {
    private GameImpl game;
    /**
     * Fixture for epsilonCiv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(GameType.epsilonCiv, 2);
    }

    @Test
    public void winnerIsFirstWithThreeSuccessfulAttacks(){

    }
}