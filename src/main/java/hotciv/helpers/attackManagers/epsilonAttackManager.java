package hotciv.helpers.attackManagers;

import hotciv.framework.*;
import hotciv.standard.*;
import hotciv.helper_Interfaces.attackManager;

public class epsilonAttackManager implements attackManager {

    //Need to aggregate combined attack/defense of the attacking and defending units so we can calculate the outcome of Attack

    public int getCombinedStrength(Game game, Unit unit){
        return 0;
    }

    public int getAttackStrength(Game game, Unit unit){
        return 0;
    }

    public int getTerrainFactor(Game game, Unit unit){
        return 0;
    }



}