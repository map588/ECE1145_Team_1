package hotciv.manager_factories;

import hotciv.framework.ManagerFactory;
import hotciv.framework.*;
import hotciv.helper_Interfaces.*;
import hotciv.helpers.actionManagers.*;
import hotciv.helpers.attackManagers.*;
import hotciv.helpers.ageManagers.*;
import hotciv.helpers.winnerManagers.*;
import hotciv.helpers.worldManagers.*;
import hotciv.helpers.roundManagers.*;
import hotciv.object_factories.*;
import hotciv.framework.GameType;

public class alphaManagerFactory implements ManagerFactory {

    public GameType getGameRules() {return GameType.alphaCiv;}

    //Unit Factory
    public UnitFactory createUnitFactory() { return new alphaUnitFactory();}

    //All default managers
    public actionManager createActionManager() {
        return new alphaActionManager();
    }
    public attackManager createAttackManager() {
        return new alphaAttackManager();
    }
    public ageManager    createAgeManager() {
        return new alphaAgeManager();
    }
    public winnerManager createWinnerManager() {
        return new alphaWinnerManager();
    }
    public worldManager  createWorldManager() {
        return new alphaWorld();
    }
    public roundManager  createRoundManager() {
        return new alphaRoundManager();
    }
}
