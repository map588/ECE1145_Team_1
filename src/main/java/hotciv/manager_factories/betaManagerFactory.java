package hotciv.manager_factories;

import hotciv.framework.GameType;
import hotciv.framework.ManagerFactory;
import hotciv.framework.UnitFactory;
import hotciv.helper_Interfaces.*;
import hotciv.helpers.actionManagers.*;
import hotciv.helpers.attackManagers.*;
import hotciv.helpers.ageManagers.*;
import hotciv.helpers.winnerManagers.*;
import hotciv.helpers.worldManagers.*;
import hotciv.helpers.roundManagers.*;
import hotciv.object_factories.alphaUnitFactory;

public class betaManagerFactory implements ManagerFactory {

    //beta variant
    public GameType getGameRules() {return GameType.betaCiv;}
    public ageManager createAgeManager() {return new betaAgeManager();}
    public winnerManager createWinnerManager() {
        return new betaWinnerManager();
    }

    //Unit Factory
    public UnitFactory createUnitFactory() { return new alphaUnitFactory();}

    public actionManager createActionManager() {
        return new alphaActionManager();
    }
    public attackManager createAttackManager() {
        return  new alphaAttackManager();
    }
    public worldManager createWorldManager() {
        return new alphaWorld();
    }
    public roundManager createRoundManager() {
        return new alphaRoundManager();
    }
}
