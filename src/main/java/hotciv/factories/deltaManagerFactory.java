package hotciv.factories;

import hotciv.framework.ManagerFactory;
import hotciv.helper_Interfaces.*;
import hotciv.helpers.actionManagers.*;
import hotciv.helpers.attackManagers.*;
import hotciv.helpers.ageManagers.*;
import hotciv.helpers.winnerManagers.*;
import hotciv.helpers.worldManagers.*;
import hotciv.helpers.roundManagers.*;

public class deltaManagerFactory implements ManagerFactory {

    // delta variant
    public worldManager createWorldManager() {
        return new deltaWorld();
    }



    public actionManager createActionManager() {
        return new alphaActionManager();
    }
    public attackManager createAttackManager() {
        return new alphaAttackManager();
    }
    public ageManager createAgeManager() {
        return new alphaAgeManager();
    }
    public winnerManager createWinnerManager() {
        return new alphaWinnerManager();
    }
    public roundManager createRoundManager() {
        return new alphaRoundManager();
    }
}
