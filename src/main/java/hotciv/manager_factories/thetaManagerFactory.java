package hotciv.manager_factories;

import hotciv.framework.ManagerFactory;
import hotciv.framework.UnitFactory;
import hotciv.helper_Interfaces.*;
import hotciv.helpers.actionManagers.*;
import hotciv.helpers.ageManagers.*;
import hotciv.helpers.attackManagers.*;
import hotciv.helpers.roundManagers.*;
import hotciv.helpers.winnerManagers.*;
import hotciv.helpers.worldManagers.*;
import hotciv.object_factories.*;

public class thetaManagerFactory implements ManagerFactory {
    //gamma variant
    public actionManager createActionManager() {
        return new thetaActionManager();
    }
    //Unit Factory
    public UnitFactory createUnitFactory() { return new thetaUnitFactory();}


    public attackManager createAttackManager() {
        return new alphaAttackManager();
    }
    public ageManager createAgeManager() {
        return new alphaAgeManager();
    }
    public winnerManager createWinnerManager() {
        return new alphaWinnerManager();
    }
    public worldManager createWorldManager() {
        return new alphaWorld();
    }
    public roundManager createRoundManager() {
        return new alphaRoundManager();
    }
}
