package hotciv.helper_Interfaces;

import hotciv.framework.*;
import hotciv.standard.*;


public interface attackManager {


    /**
     * This method is called when a unit occupies the tile of  another unit.
     * @param attacker
     * @param defender
     * @param g
     * @return true if the attack was successful, false otherwise.
     */

    default public boolean attack(Position attacker, Position defender, GameImpl g){

        UnitImpl attackingUnit = g.getUnitAt(attacker);
        UnitImpl defendingUnit = g.getUnitAt(defender);
        g.incrementNumberOfSuccessfulAttacks(attacker);

        g.removeUnitAt(defender);


        return true;
    }





    default public int getCombAttackStrength(GameImpl g, Unit unit){
        return 0;
    }

    default public int getCombDefenderStrength(GameImpl g, Unit unit){
        return 0;
    }

    default public void setCombAttackerStrength(GameImpl g, Unit unit){
    }

    default public void setCombDefenderStrength(GameImpl g, Unit unit){
    }

    default public void dieRoll(){
    }

    default public void setTestDieValues(int attacker, int defender){
    }

    default public boolean determineVictor(){
        return true;
    }

    default public void setTestMode(){
    }

    default public void setNormalMode(){
    }


}
