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
    
    public actionManager createActionManager() {
        return new betaActionManager();
    }

    
    public attackManager createAttackManager() {
        return  new betaAttackManager();
    }

    
    public ageManager createAgeManager() {
        return new betaAgeManager();
    }

    
    public winnerManager createWinnerManager() {
        return new betaWinnerManager();
    }

    
    public worldManager createWorldManager() {
        return new betaWorld();
    }

    
    public roundManager createRoundManager() {
        return new betaRoundManager();
    }
}
