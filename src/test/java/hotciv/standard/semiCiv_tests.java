package hotciv.standard;

import hotciv.framework.*;
import hotciv.helper_Interfaces.*;
import hotciv.helpers.attackManagers.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import static hotciv.framework.GameConstants.ARCHER;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class semiCiv_tests {


    private GameImpl game;
    private attackManager semiAttManager;

    @Before
    public void setUp() {
        game = new GameImpl(GameType.semiCiv, 2);
        semiAttManager = game.getAttack_manager();

    }

    // ************* EpsilonCiv Tests *************
    @Test
    public void winnerIsFirstWithThreeSuccessfulAttacks(){
        Position unit1 = new Position(1, 1);
        game.createUnitAt(unit1, ARCHER, Player.RED);
        game.incrementNumberOfSuccessfulAttacks(unit1);
        assertNull(game.getWinner());
        game.incrementNumberOfSuccessfulAttacks(unit1);
        assertNull(game.getWinner());
        game.incrementNumberOfSuccessfulAttacks(unit1);
        assertThat(game.getWinner(), is(Player.RED));

    }

    @Test
    public void testAttackOutcome() {

        Position Runit1 = new Position(1, 1);
        Position Runit2 = new Position(2, 1);
        Position Bunit1 = new Position(1, 4);
        Position Bunit2 = new Position(2, 4);
        Position Bunit3 = new Position(1, 5);
        Position Bunit4 = new Position(3, 4);
        game.createUnitAt(Runit1, ARCHER, Player.RED);
        UnitImpl Red1 = game.getUnitAt(Runit1);
        Red1.setAttackingStrength(1);
        game.createUnitAt(Runit2, ARCHER, Player.RED);
        UnitImpl Red2 = game.getUnitAt(Runit2);
        Red2.setAttackingStrength(1);
        Red2.setDefensiveStrength(6);
        game.createUnitAt(Bunit1, ARCHER, Player.BLUE);
        UnitImpl Blue1 = game.getUnitAt(Bunit1);
        Blue1.setDefensiveStrength(1);
        game.createUnitAt(Bunit2, ARCHER, Player.BLUE);
        UnitImpl Blue2 = game.getUnitAt(Bunit2);
        Blue2.setDefensiveStrength(1);
        game.createUnitAt(Bunit3, ARCHER, Player.BLUE);
        UnitImpl Blue3 = game.getUnitAt(Bunit3);
        Blue3.setDefensiveStrength(1);
        game.createUnitAt(Bunit4, ARCHER, Player.BLUE);
        UnitImpl Blue4 = game.getUnitAt(Bunit4);
        Blue4.setDefensiveStrength(1);
        Blue4.setAttackingStrength(1);

        semiAttManager.setTestDieValues(4,1);
        semiAttManager.setTestMode();
        assertThat(game.attack(Runit1, Bunit1), is(true));
        assertThat(game.getUnitOwner(Bunit1), is(Player.RED)); //checks that the winning attacker moves to the defender's old location
        assertNull(game.getUnitAt(Runit1)); //ensures original place of red unit is clear
        assertThat(game.attack(Bunit1, Bunit2), is(true)); //red unit attacks blue unit and wins
        assertThat(game.attack(Bunit4, Runit2), is(false)); //blue attacks red and loses
        assertNull(game.getUnitAt(Bunit4)); //no unit at this location (died)
        assertThat(game.getUnitAt(Runit2), is(Red2)); //successful defender stays in location
        assertThat(game.attack(Bunit2, Bunit3), is(true)); //red attacks blue and wins
        assertThat(game.getWinner(), is(Player.RED)); //player Red has won after 3 successful attacks
    }



    // ************* BetaCiv Tests *************

    @Test
    public void betaCiv_DynamicWorldAging() {

        // 100 Years per turn pre- 100BC
        game.progressRounds(39);
        assertThat(game.getAge(), is(-100));

        // Next turn is 1BC

        game.progressRounds(1);
        assertThat(game.getAge(), is(-1));

        // Next turn is 1AD
        game.progressRounds(1);
        assertThat(game.getAge(), is(1));

        // Next turn is 50AD
        game.progressRounds(1);
        assertThat(game.getAge(), is(50));


        // 50 years per turn until 1750
        game.progressRounds(34);
        assertThat(game.getAge(), is(1750));

        // 25 years per turn until 1900
        game.progressRounds(6);
        assertThat(game.getAge(), is(1900));

        // 5 years per turn until 1970
        game.progressRounds(14);
        assertThat(game.getAge(), is(1970));

        // 1 year per turn for the rest of the game
        game.progressRounds(100);
        assertThat(game.getAge(), is(2070));
    }

    // ************* GammaCiv Tests *************

    @Test
    public void settlerActionToCity(){
        //Player red has a settler at (3,4) in the test game constructor
        Position posSettler = new Position(3, 4);
        //game.setCityAt(posSettler, game.getUnitAt(posSettler).getOwner()); //old code
        game.performUnitActionAt(posSettler); //with refactoring
        assertThat(game.getCityAt(posSettler).getOwner(), is(Player.RED) );
        assertThat(game.getCityAt(posSettler).getSize(), is(1));
        assertNull(game.getUnitAt(posSettler));
    }

    @Test
    public void archerFortifyDoublesDefense(){ //simulate fortifying an archer
        Position posArcher = new Position(0,2);
        game.getUnitAt(posArcher).setDefensiveStrength(2);
        game.performUnitActionAt(posArcher);
        assertThat(game.getUnitAt(posArcher).getDefensiveStrength(), is(4));
    }
    @Test
    public void archerFortifyHalvesDefense(){ //simulate fortifying an already fortified archer
        Position posArcher = new Position(0,2);
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
        game.performUnitActionAt(posSettler);
        assertThat(game.getCityAt(posSettler).getOwner(), is(Player.RED) );
        assertThat(game.getCityAt(posSettler).getSize(), is(1));
        assertNull(game.getUnitAt(posSettler));
    }

    @Test
    public void archerFortifyDoublesDefenseIntegrated(){ //simulate fortifying an archer
        Position posArcher = new Position(0,2);
        game.getUnitAt(posArcher).setDefensiveStrength(2);
        game.performUnitActionAt(posArcher);
        assertThat(game.getUnitAt(posArcher).getDefensiveStrength(), is(4));
    }
    @Test
    public void archerFortifyHalvesDefenseIntegrated(){ //simulate fortifying an already fortified archer
        Position posArcher = new Position(0,2);
        game.getUnitAt(posArcher).setDefensiveStrength(1);
        game.getUnitAt(posArcher).fortify();
        game.performUnitActionAt(posArcher);
        assertThat(game.getUnitAt(posArcher).getDefensiveStrength(), is(1));
    }


    // ************* EtaCiv Tests *************

    @Test
    public void productionFocus() {
        // When a city is set to production focus, the citizens are assigned to the tiles adjacent
        // to the city that offer the most production. The city must have a citizen assigned to it.
    }

    @Test
    public void foodFocus() {
        // When a city is set to food focus, the citizens are assigned to the tiles adjacent
        // to the city that offer the most food. The city must have a citizen assigned to it.
    }


    // CITY POPULATION TESTS...
    @Test
    public void populationGrowth() {
        // population size in the city increases by one once the total collected food
        // in the city exceeds 5 + (city size)*3 .
    }

    @Test
    public void maxPopulation() {
        // population size is capped at 9
    }

    @Test
    public void foodResetOnPopulationIncrease() {
        // food count is reset to 0 when the population increases.
    }

}