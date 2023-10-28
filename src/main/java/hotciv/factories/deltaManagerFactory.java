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
    
    public actionManager createActionManager() {
        return new deltaActionManager();
    }

    
    public attackManager createAttackManager() {
        return new deltaAttackManager();
    }

    
    public ageManager createAgeManager() {
        return new deltaAgeManager();
    }

    
    public winnerManager createWinnerManager() {
        return new deltaWinnerManager();
    }

    
    public worldManager createWorldManager() {
        return new deltaWorld();
    }

    
    public roundManager createRoundManager() {
        return new deltaRoundManager();
    }
}
