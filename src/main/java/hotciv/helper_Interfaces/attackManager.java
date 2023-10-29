package hotciv.helper_Interfaces;

import hotciv.framework.*;
import hotciv.standard.*;
import hotciv.standard.utility.Utility;

import java.util.Iterator;
import java.util.Random;


public interface attackManager {



    default public boolean attack(Position attacker, Position defender, GameImpl g){

        Unit attackingUnit = g.getUnitAt(attacker);
        Unit defendingUnit = g.getUnitAt(defender);
        g.incrementNumberOfSuccessfulAttacks(attacker);


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
