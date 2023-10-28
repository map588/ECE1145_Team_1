package hotciv.factories;

import hotciv.framework.ManagerFactory;
import hotciv.helper_Interfaces.*;
import hotciv.helpers.actionManagers.*;
import hotciv.helpers.attackManagers.*;
import hotciv.helpers.ageManagers.*;
import hotciv.helpers.winnerManagers.*;
import hotciv.helpers.worldManagers.*;
import hotciv.helpers.roundManagers.*;


public class zetaManagerFactory implements ManagerFactory {

    public actionManager createActionManager() {
        return new zetaActionManager();
    }


    public attackManager createAttackManager() {
        return new zetaAttackManager();
    }


    public ageManager createAgeManager() {
        return  new zetaAgeManager();
    }


    public winnerManager createWinnerManager() {
        return new zetaWinnerManager();
    }


    public worldManager createWorldManager() {
        return new zetaWorld();
    }


    public roundManager createRoundManager() {
        return  new zetaRoundManager();
    }
}
