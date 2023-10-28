package hotciv.helper_Interfaces;

import hotciv.framework.*;
import hotciv.standard.*;


public interface attackManager {


    default public int attack(Position attacker, Position defender, Game g){

        Unit attackingUnit = g.getUnitAt(attacker);
        Unit defendingUnit = g.getUnitAt(defender);





        return 0;
    }



}
