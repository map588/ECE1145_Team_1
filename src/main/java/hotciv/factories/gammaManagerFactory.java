package hotciv.factories;

import hotciv.framework.ManagerFactory;
import hotciv.helper_Interfaces.*;
import hotciv.helpers.actionManagers.*;
import hotciv.helpers.attackManagers.*;
import hotciv.helpers.ageManagers.*;
import hotciv.helpers.winnerManagers.*;
import hotciv.helpers.worldManagers.*;
import hotciv.helpers.roundManagers.*;

public class gammaManagerFactory implements ManagerFactory {
    
    public actionManager createActionManager() {
        return new gammaActionManager();
    }

    
    public attackManager createAttackManager() {
        return new gammaAttackManager();
    }

    
    public ageManager createAgeManager() {
        return new gammaAgeManager();
    }

    
    public winnerManager createWinnerManager() {
        return new gammaWinnerManager();
    }

    
    public worldManager createWorldManager() {
        return new gammaWorld();
    }

    
    public roundManager createRoundManager() {
        return new gammaRoundManager();
    }
}
