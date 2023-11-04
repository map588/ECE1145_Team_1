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

public class epsilonManagerFactory implements ManagerFactory {

    //epsilon variant
    public winnerManager createWinnerManager() {
        return new epsilonWinnerManager();
    }

    //epsilon variant
    public attackManager createAttackManager() {
        return new epsilonAttackManager();
    }


    //Unit Factory
    public UnitFactory createUnitFactory() { return new alphaUnitFactory();}



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
