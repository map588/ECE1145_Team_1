package hotciv.standard;

import hotciv.framework.*;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class zetaCiv_tests {
    private Game game;
    /**
     * Fixture for zetaCiv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(GameType.zetaCiv, 2);
    }
}