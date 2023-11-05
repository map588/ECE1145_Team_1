package hotciv.standard;

import hotciv.framework.*;

import org.junit.Before;
import org.junit.Test;

import static hotciv.framework.GameConstants.ARCHER;
import static hotciv.framework.GameConstants.SETTLER;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;


public class thetaCiv_tests {
    private GameImpl game; //Changed Game to GameImpl -TPD

    /**
     * Fixture for thetaCiv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(GameType.thetaCiv, 2);
    }

    //..........Unit Tests...........//

    @Test
    public void settlerActionToCity(){
        //Player red has a settler at (3,4) in the test game constructor
        Position posSettler = new Position(3, 4);
        game.createUnitAt(posSettler, SETTLER, Player.RED);
        //game.setCityAt(posSettler, game.getUnitAt(posSettler).getOwner()); //old code
        game.performUnitActionAt(posSettler); //with refactoring
        assertThat(game.getCityAt(posSettler).getOwner(), is(Player.RED) );
        assertThat(game.getCityAt(posSettler).getSize(), is(1));
        assertNull(game.getUnitAt(posSettler));
    }

    @Test
    public void archerFortifyDoublesDefense(){ //simulate fortifying an archer
        Position posArcher = new Position(0,2);
        game.createUnitAt(posArcher, ARCHER, Player.RED);
        game.getUnitAt(posArcher).setDefensiveStrength(2);
        game.performUnitActionAt(posArcher);
        assertThat(game.getUnitAt(posArcher).getDefensiveStrength(), is(4));
    }
    @Test
    public void archerFortifyHalvesDefense(){ //simulate fortifying an already fortified archer
        Position posArcher = new Position(0,2);
        game.createUnitAt(posArcher, ARCHER, Player.RED);
        game.getUnitAt(posArcher).setDefensiveStrength(1);
        game.getUnitAt(posArcher).fortify();
        game.performUnitActionAt(posArcher);
        assertThat(game.getUnitAt(posArcher).getDefensiveStrength(), is(1));
    }

    //...............Integration Testing...................//

    @Test
    public void settlerActionToCityIntegrated(){
        //Player red has a settler at (3,4) in the test game constructor
        Position posSettler = new Position(3, 4);
        game.createUnitAt(posSettler, SETTLER, Player.RED);
        game.performUnitActionAt(posSettler);
        assertThat(game.getCityAt(posSettler).getOwner(), is(Player.RED) );
        assertThat(game.getCityAt(posSettler).getSize(), is(1));
        assertNull(game.getUnitAt(posSettler));
    }

    @Test
    public void archerFortifyDoublesDefenseIntegrated(){ //simulate fortifying an archer
        Position posArcher = new Position(0,2);
        game.createUnitAt(posArcher, ARCHER, Player.RED);
        game.getUnitAt(posArcher).setDefensiveStrength(2);
        game.performUnitActionAt(posArcher);
        assertThat(game.getUnitAt(posArcher).getDefensiveStrength(), is(4));
    }
    @Test
    public void archerFortifyHalvesDefenseIntegrated(){ //simulate fortifying an already fortified archer
        Position posArcher = new Position(0,2);
        game.createUnitAt(posArcher, ARCHER, Player.RED);
        game.getUnitAt(posArcher).setDefensiveStrength(1);
        game.getUnitAt(posArcher).fortify();
        game.performUnitActionAt(posArcher);
        assertThat(game.getUnitAt(posArcher).getDefensiveStrength(), is(1));
    }
}