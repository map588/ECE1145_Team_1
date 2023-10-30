package hotciv.factories;

import hotciv.framework.ManagerFactory;
import hotciv.helper_Interfaces.*;
import hotciv.helpers.actionManagers.*;
import hotciv.helpers.attackManagers.*;
import hotciv.helpers.ageManagers.*;
import hotciv.helpers.winnerManagers.*;
import hotciv.helpers.worldManagers.*;
import hotciv.helpers.roundManagers.*;


public class etaManagerFactory implements ManagerFactory {

    public actionManager createActionManager() {
        return new etaActionManager();
    }


    public attackManager createAttackManager() {
        return new etaAttackManager();
    }


    public ageManager createAgeManager() {
        return  new etaAgeManager();
    }


    public winnerManager createWinnerManager() {
        return new etaWinnerManager();
    }


    public worldManager createWorldManager() {
        return new etaWorld();
    }


    public roundManager createRoundManager() {
        return  new etaRoundManager();
    }
}
