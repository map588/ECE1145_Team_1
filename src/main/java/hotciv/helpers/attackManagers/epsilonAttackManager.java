package hotciv.helpers.attackManagers;

import java.util.Iterator;
import java.util.Random;

import hotciv.framework.*;
import hotciv.helper_Interfaces.attackManager;
import hotciv.standard.GameImpl;
import hotciv.standard.utility.Utility;

public class epsilonAttackManager implements attackManager {

    private int CombAttackStrength;
    private int CombDefendStrength;
    private int numberAdjacentAttackUnits;
    private int numberAdjacentDefenceUnits;
    private static Position attackerPos;
    private static Position defenderPos;
    private static int attackerRoll;
    private static int defenderRoll;

    @Override
    public boolean attack(Position attacker, Position defender, GameImpl g){
        attackerPos = attacker;
        defenderPos = defender;
        Unit attackingUnit = g.getUnitAt(attacker);
        Unit defendingUnit = g.getUnitAt(defender);
        attackerRoll = dieRoll();
        defenderRoll = dieRoll();

        //**************************** Hard coded true for troubleshooting/setup
        boolean winner = true; //determineVictor(); //true if attacker, false if defender

        if (winner){
            g.incrementNumberOfSuccessfulAttacks(attackerPos);
            g.removeUnitAt(defenderPos);
            g.moveUnit(attackerPos, defenderPos);
            return true;
        }
        else {
            g.removeUnitAt(attackerPos);
            return false;
        }
    }


    public int getCombAttackStrength(GameImpl g, Unit unit){
        return CombAttackStrength;
    }

    public int getCombDefenderStrength(GameImpl g, Unit unit){
        return CombDefendStrength;
    }

    public void setCombAttackerStrength(GameImpl g, Unit unit){
        CombAttackStrength = getTerrainFactor(g, attackerPos) *
                (g.getAttackStrength(unit) + getFriendlySupport(g, attackerPos, g.getUnitOwner(attackerPos)));
    }

    public void setCombDefenderStrength(GameImpl g, Unit unit){
        CombDefendStrength = getTerrainFactor(g, defenderPos) *
                (g.getDefensiveStrength(unit) + getFriendlySupport(g, defenderPos, g.getUnitOwner(defenderPos)));
    }


    /**
     * calculate the additional support a unit at position p owned by a given
     * player gets from friendly units on the given game.
     *
     * @param game
     * the game to calculate on
     * @param position
     * the position of the unit whose support is wanted
     * @param player
     * the player owning the unit at position 'position'
     * @return the support for the unit, +1 for each friendly unit in the 8
     * neighborhood.
     */
    public static int getFriendlySupport(GameImpl g, Position position, Player player) {
        Iterator<Position> neighborhood = Utility.get8neighborhoodIterator(position);
        Position p;
        int support = 0;
        while (neighborhood.hasNext()) {
            p = neighborhood.next();
            if (g.getUnitAt(p) != null &&
                    g.getUnitAt(p).getOwner() == player) {
                support++;
            }
        }
        return support;
    }

    /**
     * get the terrain factor for the attack and defense strength according to the
     * GammaCiv specification
     *
     * @param game
     * the game the factor should be given for
     * @param position
     * the position that the factor should be calculated for
     * @return the terrain factor
     */
    public static int getTerrainFactor(Game game, Position position) {
// cities overrule underlying terrain
        if ( game.getCityAt(position) != null ) { return 3; }
        Tile t = game.getTileAt(position);
        if ( t.getTypeString() == GameConstants.FOREST ||
                t.getTypeString() == GameConstants.HILLS ) {
            return 2;
        }
        return 1;
    }

    public int dieRoll(){
        Random random = new Random();
        // Simulate a six-sided die roll
        int min = 1;
        int max = 6;
        int dieRoll = random.nextInt(max - min + 1) + min;
        return dieRoll;
    }

    private boolean determineVictor(){
        if ((CombAttackStrength * attackerRoll) > (CombDefendStrength * defenderRoll)){
            return true;
        }
        return false;
    }

}