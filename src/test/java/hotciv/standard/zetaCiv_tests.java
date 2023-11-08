package hotciv.standard;

import hotciv.framework.*;

import hotciv.manager_factories.zetaManagerFactory;
import org.junit.Before;
import org.junit.Test;

import static hotciv.framework.GameConstants.ARCHER;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class zetaCiv_tests {
    private GameImpl game;
    /**
     * Fixture for zetaCiv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(new zetaManagerFactory(), 2);
    }

    @Test
    public void zetaCiv_FirstPlayerThatConquersWorldWins() {
        Position city1 = new Position(1, 1);
        Position city2 = new Position(1, 4);

        game.setCityAt(city1, Player.BLUE);
        game.setCityAt(city2, Player.BLUE);

        assertThat(game.getCityAt(city1).getOwner(), is(Player.BLUE));
        assertThat(game.getWinner(), is(Player.BLUE)); // Blue owns both cities and should win
    }
    @Test
    public void winnerAfter20Rounds(){
        game.progressRounds(20);

        Position unit1 = new Position(1, 1);
        game.createUnitAt(unit1, ARCHER, Player.RED);
        game.incrementNumberOfSuccessfulAttacks(unit1);
        assertNull(game.getWinner());
        game.incrementNumberOfSuccessfulAttacks(unit1);
        assertNull(game.getWinner());
        game.incrementNumberOfSuccessfulAttacks(unit1);
        assertThat(game.getWinner(), is(Player.RED));
    }
}