package hotciv.helpers.attackManagers;

import java.util.Iterator;
import java.util.Objects;
import java.util.Random;

import hotciv.framework.*;
import hotciv.helper_Interfaces.attackManager;
import hotciv.standard.GameImpl;
import hotciv.utility.Utility;

public class epsilonAttackManager implements attackManager {

    static Random random = new Random();

    private int CombAttackStrength;
    private int CombDefendStrength;
    private  Position attackerPos;
    private  Position defenderPos;
    public int attackerRoll;
    public int defenderRoll;
    public int attackerRollTest;
    public int defenderRollTest;
    private boolean isTestCase = false;

    @Override
    public boolean attack(Position attacker, Position defender, GameImpl g){
        attackerPos = attacker;
        defenderPos = defender;
        Unit attackingUnit = g.getUnitAt(attacker);
        Unit defendingUnit = g.getUnitAt(defender);
        setCombAttackerStrength(g, attackingUnit);
        setCombDefenderStrength(g, defendingUnit);

        if (isTestCase){
            attackerRoll = attackerRollTest;
            defenderRoll = defenderRollTest;
        }
        else {
            attackerRoll = random.nextInt(6) + 1;
            defenderRoll = random.nextInt(6) + 1;
        }
        //nexInt(6) + 1 = rand(0, 5) + 1 = rand(1, 6)
        //attackerRoll = random.nextInt(6) + 1;
        //defenderRoll = random.nextInt(6) + 1;

        boolean battle_won = (CombAttackStrength * attackerRoll) > (CombDefendStrength * defenderRoll);

        if (battle_won){
            g.incrementNumberOfSuccessfulAttacks(attackerPos);
            g.removeUnitAt(defenderPos);
            return true;
        }
        else {
            g.removeUnitAt(attackerPos);
            return false;
        }
    }


    @Override
    public int getCombAttackStrength(GameImpl g, Unit unit){
        return CombAttackStrength;
    }

    @Override
    public int getCombDefenderStrength(GameImpl g, Unit unit){
        return CombDefendStrength;
    }

    @Override
    public void setCombAttackerStrength(GameImpl g, Unit unit){
        CombAttackStrength = getTerrainFactor(g, attackerPos) *
                (g.getAttackStrength(unit) + getFriendlySupport(g, attackerPos, g.getUnitOwner(attackerPos)));
    }

    @Override
    public void setCombDefenderStrength(GameImpl g, Unit unit){
        CombDefendStrength = getTerrainFactor(g, defenderPos) *
                (g.getDefensiveStrength(unit) + getFriendlySupport(g, defenderPos, g.getUnitOwner(defenderPos)));
    }


    /**
     * calculate the additional support a unit at position p owned by a given
     * player gets from friendly units on the given game.
     *
     * @param g
     * the game to calculate on
     * @param position
     * the position of the unit whose support is wanted
     * @param player
     * the player owning the unit at position 'position'
     * @return the support for the unit, +1 for each friendly unit in the 8
     * neighborhood.
     */


    public int getFriendlySupport(GameImpl g, Position position, Player player) {
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

    public int getTerrainFactor(Game game, Position position) {
        // cities overrule underlying terrain
        if ( game.getCityAt(position) != null ) { return 3; }
        Tile t = game.getTileAt(position);
        if (Objects.equals(t.getTypeString(), GameConstants.FOREST) ||
                Objects.equals(t.getTypeString(), GameConstants.HILLS)) {
            return 2;
        }
        return 1;
    }


    @Override
    public void setTestDieValues(int attacker, int defender){
        attackerRollTest = attacker;
        defenderRollTest = defender;
    }

    @Override
    public void setTestMode(){
        isTestCase = true;
    }

    @Override
    public void setNormalMode(){
        isTestCase = false;
    }

}