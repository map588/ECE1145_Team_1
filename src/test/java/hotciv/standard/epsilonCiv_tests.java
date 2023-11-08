package hotciv.standard;

import hotciv.framework.*;
import hotciv.helper_Interfaces.*;
import hotciv.helpers.attackManagers.*;

import hotciv.manager_factories.epsilonManagerFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import static hotciv.framework.GameConstants.ARCHER;
import static org.hamcrest.CoreMatchers.is;

public class epsilonCiv_tests {


    private GameImpl game;
    private attackManager epAttManager;

    @Before
    public void setUp() {
        game = new GameImpl(new epsilonManagerFactory(), 2);
    }

    @Test
    public void winnerIsFirstWithThreeSuccessfulAttacks() {
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

//        Position R_unit1 = new Position(1, 1);
//        Position R_unit2 = new Position(2, 1);
//        Position B_unit1 = new Position(1, 2);
//        Position B_unit2 = new Position(2, 2);
//        Position B_unit3 = new Position(3, 2);
//        Position B_unit4 = new Position(3, 1);
//        game.createUnitAt(R_unit1, ARCHER, Player.RED);
//        game.getUnitAt(R_unit1).setAttackingStrength(20);
//        game.createUnitAt(R_unit2, ARCHER, Player.RED);
//        game.getUnitAt(R_unit2).setAttackingStrength(1);
//        game.getUnitAt(R_unit2).setDefensiveStrength(10);
//        game.createUnitAt(B_unit1, ARCHER, Player.BLUE);
//        game.getUnitAt(B_unit1).setDefensiveStrength(1);
//        game.createUnitAt(B_unit2, ARCHER, Player.BLUE);
//        game.getUnitAt(B_unit2).setDefensiveStrength(1);
//        game.createUnitAt(B_unit3, ARCHER, Player.BLUE);
//        game.getUnitAt(B_unit3).setDefensiveStrength(1);
//        game.createUnitAt(B_unit4, ARCHER, Player.BLUE);
//        game.getUnitAt(B_unit4).setDefensiveStrength(1);
//        game.getUnitAt(B_unit4).setAttackingStrength(1);

        //R1 attacks B1
        //R1 attacks B2
        //B4 attacks R2
        //R1 attacks B3

        // |R1|R2|B4|  |
        // |B1|B2|  |  |
        // |  |B3|  |  |
        // |  |  |  |  |

//        epAttManager.setTestDieValues(4, 1);
//        epAttManager.setTestMode();
//        assertThat(game.moveUnit(R_unit1, B_unit1), is(true));
//        assertThat(game.getUnitOwner(B_unit1), is(Player.RED)); //checks that the winning attacker moves to the defender's old location
//        assertNull(game.getUnitAt(R_unit1)); //ensures original place of red unit is clear
//        game.progressRounds(1);
//        assertThat(game.moveUnit(B_unit1, B_unit2), is(true)); //red unit attacks blue unit and wins
//        assertThat(game.moveUnit(B_unit4, R_unit2), is(false)); //blue attacks red and loses
//        assertNull(game.getUnitAt(B_unit4)); //no unit at this location (died)
//        game.progressRounds(1);
//        assertThat(game.moveUnit(B_unit2, B_unit3), is(true)); //red attacks blue and wins
//        assertThat(game.getWinner(), is(Player.RED)); //player Red has won after 3 successful attacks

        Position P1  = new Position(1, 1);
        Position P2  = new Position(2, 2);

        //default 2 attack 3 defense
        game.createUnitAt(P1, ARCHER, Player.RED);
        game.createUnitAt(P2, ARCHER, Player.BLUE);

        game.toggleWinnerManager(false);

        int redWins = 0;
        int blueWins = 0;

        for(int i = 0; i < 100; i++) {
            if(game.moveUnit(P1, P2)){
                game.endOfTurn();
                game.createUnitAt(P1, ARCHER, game.getPlayerInTurn());
                if(game.getPlayerInTurn() == Player.RED) {
                    redWins++;
                }else{
                    blueWins++;
                }
                if (game.getPlayerInTurn() == Player.RED) {
                    redWins++;
                } else {
                    blueWins++;
                }
            }else{
                game.createUnitAt(P1, ARCHER, game.getPlayerInTurn());
                game.endOfTurn();
                if (game.getPlayerInTurn() == Player.RED) {
                    redWins++;
                } else {
                    blueWins++;
                }
            }
            assertNotNull(game.getUnitAt(P1));
            assertNotNull(game.getUnitAt(P2));
        }





    }
}