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

    //gamma actions
    public actionManager createActionManager() {
        return new gammaActionManager();
    }

    //epsilon attacks
    public attackManager createAttackManager() {
        return new epsilonAttackManager();
    }

    //beta aging
    public ageManager createAgeManager() {
        return  new betaAgeManager();
    }

    //epsilon winner
    public winnerManager createWinnerManager() {
        return new epsilonWinnerManager();
    }

    //delta world
    public worldManager createWorldManager() {
        return new deltaWorld();
    }

    //alpha round
    public roundManager createRoundManager() {
        return  new alphaRoundManager();
    }
}
