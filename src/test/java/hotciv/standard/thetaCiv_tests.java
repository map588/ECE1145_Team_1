package hotciv.standard;

import hotciv.framework.*;

import org.junit.Before;
import org.junit.Test;

import static hotciv.framework.GameConstants.*;
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

    //............Unique Theta Stuff............//


    @Test
    public void ufoHas1Attack8Defense(){
        Position posUFO = new Position(3, 4);
        game.createUnitAt(posUFO, UFO, Player.RED);
        assertThat(game.getUnitAt(posUFO).getAttackingStrength(), is(1));
        assertThat(game.getUnitAt(posUFO).getDefensiveStrength(), is(8));
    }

    @Test
    public void ufoHasTravelDistance2(){
        Position posUFO = new Position(3, 4);
        game.createUnitAt(posUFO, UFO, Player.RED);
        assertThat(game.getUnitAt(posUFO).getMoveCount(), is(2));
    }

    @Test
    public void ufoCanTravelOverOceans(){
        Position posUFO = new Position(3, 4);
        game.createUnitAt(posUFO, UFO, Player.RED);
        Position posOcean = new Position(3, 5);
        assertThat(game.getUnitAt(posUFO).getMoveCount(), is(2));
        game.moveUnit(posUFO, posOcean);
        assertThat(game.getUnitAt(posOcean).getTypeString(), is(UFO));
    }

    @Test
    public void ufoCanTravelOverMountains(){
        Position posUFO = new Position(3, 4);
        game.createUnitAt(posUFO, UFO, Player.RED);
        Position posMountain = new Position(4, 4);
        assertThat(game.getUnitAt(posUFO).getMoveCount(), is(2));
        game.moveUnit(posUFO, posMountain);
        assertThat(game.getUnitAt(posMountain).getTypeString(), is(UFO));
    }

    @Test
    public void cityCanProduceUFO(){
        Position posCity = new Position(4, 4);
        game.setCityAt(posCity, Player.RED);
        game.getCityAt(posCity).setProductionRate(60);
        game.changeProductionInCityAt(posCity, UFO);
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getUnitAt(posCity).getTypeString(), is(UFO));
    }

    @Test
    public void ufoCanFlyOverEnemyCityIfThereAreNoUnits(){
        Position posUFO = new Position(3, 4);
        game.createUnitAt(posUFO, UFO, Player.RED);
        Position posCity = new Position(4, 4);
        game.setCityAt(posCity, Player.BLUE);
        game.moveUnit(posUFO, posCity);
        assertThat(game.getUnitAt(posCity).getTypeString(), is(UFO));
        assertThat(game.getUnitAt(posCity).getOwner(), is(Player.RED)); //ensures ownership of ufo is correct
        assertThat(game.getCityAt(posCity).getOwner(), is(Player.BLUE)); //ensures ownership of city is correct
    }

    @Test
    public void ufoWillBatlleEnemyUnitOnEnemyCity(){
        Position posUFO = new Position(3, 4);
        game.createUnitAt(posUFO, UFO, Player.RED);
        Position posCity = new Position(4, 4);
        game.setCityAt(posCity, Player.BLUE);
        game.createUnitAt(posCity, ARCHER, Player.BLUE);
        game.moveUnit(posUFO, posCity);
        assertThat(game.getUnitAt(posCity).getTypeString(), is(UFO));
        assertThat(game.getUnitAt(posCity).getOwner(), is(Player.RED));
        assertThat(game.getCityAt(posCity).getOwner(), is(Player.BLUE));
    }

    @Test
    public void ufoAbductsCityPopulation1(){
        Position posUFO = new Position(3, 4);
        game.createUnitAt(posUFO, "ufo", Player.RED);
        game.setCityAt(posUFO, Player.BLUE); //default population 1
        game.performUnitActionAt(posUFO);
        assertNull(game.getCityAt(posUFO));
    }

    @Test
    public void ufoAbductsCityPopulation2(){
        Position posUFO = new Position(3, 4);
        game.createUnitAt(posUFO, UFO, Player.RED);
        game.setCityAt(posUFO, Player.BLUE); //default population 1
        game.getCityAt(posUFO).setPopulation(2); //increase pop to 2
        game.performUnitActionAt(posUFO);
        assertThat(game.getCityAt(posUFO).getSize(), is(1));
    }

    @Test
    public void ufoChangesForestToPlains(){
        Position posUFO = new Position(3, 4);
        game.createUnitAt(posUFO, UFO, Player.RED);
        Position posForest = new Position(3, 5);
        game.getTileAt(posForest).setTerrain(FOREST);
        game.moveUnit(posUFO, posForest);
        game.performUnitActionAt(posForest);
        assertThat(game.getTileAt(posForest).getTypeString(), is(PLAINS));
    }




    //..........Gamma Tests Carryover...........//
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