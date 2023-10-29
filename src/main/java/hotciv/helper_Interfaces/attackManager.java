package hotciv.helper_Interfaces;

import hotciv.framework.*;
import hotciv.standard.*;


public interface attackManager {



    default public boolean attack(Position attacker, Position defender, GameImpl g){

        Unit attackingUnit = g.getUnitAt(attacker);
        Unit defendingUnit = g.getUnitAt(defender);
        g.incrementNumberOfSuccessfulAttacks(attacker);




        return true;
    }



}
