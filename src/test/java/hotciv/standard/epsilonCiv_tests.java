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

public class epsilonCiv_tests {


    private GameImpl game;
    private attackManager epAttManager;

    @Before
    public void setUp() {
        game = new GameImpl(GameType.epsilonCiv, 2);
        epAttManager = game.getAttack_manager();

    }

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

        epAttManager.setTestDieValues(4,1);
        epAttManager.setTestMode();
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



}