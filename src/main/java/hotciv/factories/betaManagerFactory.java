package hotciv.factories;

import hotciv.framework.ManagerFactory;
import hotciv.helper_Interfaces.*;
import hotciv.helpers.actionManagers.*;
import hotciv.helpers.attackManagers.*;
import hotciv.helpers.ageManagers.*;
import hotciv.helpers.winnerManagers.*;
import hotciv.helpers.worldManagers.*;
import hotciv.helpers.roundManagers.*;

public class betaManagerFactory implements ManagerFactory {

    //beta variant
    public ageManager createAgeManager() {return new betaAgeManager();}

    //beta variant
    public winnerManager createWinnerManager() {
        return new betaWinnerManager();
    }


    public actionManager createActionManager() {
        return new alphaActionManager();
    }
    public attackManager createAttackManager() {
        return  new alphaAttackManager();
    }
    public worldManager createWorldManager() {
        return new alphaWorld();
    }
    public roundManager createRoundManager() {
        return new alphaRoundManager();
    }
}
