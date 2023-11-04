package hotciv.factories;

import hotciv.framework.ManagerFactory;
import hotciv.helper_Interfaces.*;
import hotciv.helpers.actionManagers.*;
import hotciv.helpers.attackManagers.*;
import hotciv.helpers.ageManagers.*;
import hotciv.helpers.winnerManagers.*;
import hotciv.helpers.worldManagers.*;
import hotciv.helpers.roundManagers.*;

public class epsilonManagerFactory implements ManagerFactory {

    //epsilon variant
    public winnerManager createWinnerManager() {
        return new epsilonWinnerManager();
    }

    //epsilon variant
    public attackManager createAttackManager() {
        return new epsilonAttackManager();
    }



    public actionManager createActionManager() {
        return new alphaActionManager();
    }
    public ageManager createAgeManager() {
        return new alphaAgeManager();
    }
    public worldManager createWorldManager() {
        return new alphaWorld();
    }
    public roundManager createRoundManager() {
        return new alphaRoundManager();
    }
}
