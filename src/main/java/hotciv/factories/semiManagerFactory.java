package hotciv.factories;

import hotciv.framework.ManagerFactory;
import hotciv.helper_Interfaces.*;
import hotciv.helpers.actionManagers.*;
import hotciv.helpers.attackManagers.*;
import hotciv.helpers.ageManagers.*;
import hotciv.helpers.winnerManagers.*;
import hotciv.helpers.worldManagers.*;
import hotciv.helpers.roundManagers.*;


public class semiManagerFactory implements ManagerFactory {

    public actionManager createActionManager() {
        return new gammaActionManager();
    }


    public attackManager createAttackManager() {
        return new epsilonAttackManager();
    }


    public ageManager createAgeManager() {
        return  new betaAgeManager();
    }


    public winnerManager createWinnerManager() {
        return new epsilonWinnerManager();
    }


    public worldManager createWorldManager() {
        return new deltaWorld();
    }


    public roundManager createRoundManager() {
        return  new alphaRoundManager();
    }
}
