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
    
    public actionManager createActionManager() {
        return new epsilonActionManager();
    }

    
    public attackManager createAttackManager() {
        return new epsilonAttackManager();
    }

    
    public ageManager createAgeManager() {
        return new epsilonAgeManager();
    }

    
    public winnerManager createWinnerManager() {
        return new epsilonWinnerManager();
    }

    
    public worldManager createWorldManager() {
        return new epsilonWorld();
    }

    
    public roundManager createRoundManager() {
        return new epsilonRoundManager();
    }
}
