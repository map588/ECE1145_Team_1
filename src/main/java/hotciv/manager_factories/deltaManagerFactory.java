package hotciv.manager_factories;

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

public class deltaManagerFactory implements ManagerFactory {

    // delta variant
    public worldManager createWorldManager() {
        return new deltaWorld();
    }


    //Unit Factory
    public UnitFactory createUnitFactory() { return new alphaUnitFactory();}


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
