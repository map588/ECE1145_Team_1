package hotciv.standard;

import hotciv.framework.*;

import org.junit.Before;
import org.junit.Test;

import static hotciv.framework.GameConstants.ARCHER;
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
        Position unit1 = new Position(1, 1);
        Position unit2 = new Position(1, 4);
        game.createUnitAt(unit1, ARCHER, Player.RED);
        game.createUnitAt(unit2, ARCHER, Player.BLUE);
        game.battle(unit1, unit2);
        assertNull(game.getWinner());
        game.battle(unit1, unit2);
        assertNull(game.getWinner());
        game.battle(unit1, unit2);
        assertThat(game.getWinner(), is(Player.RED));
    }
}