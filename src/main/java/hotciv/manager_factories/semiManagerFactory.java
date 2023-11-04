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


public class semiManagerFactory implements ManagerFactory {

    //Unit Factory
    public UnitFactory createUnitFactory() { return new alphaUnitFactory();}

    //gamma actions
    public actionManager createActionManager() {
        return new gammaActionManager();
    }

    //epsilon attacks
    public attackManager createAttackManager() {
        return new epsilonAttackManager();
    }

    //beta aging
    public ageManager createAgeManager() {
        return  new betaAgeManager();
    }

    //epsilon winner
    public winnerManager createWinnerManager() {
        return new epsilonWinnerManager();
    }

    //delta world
    public worldManager createWorldManager() {
        return new deltaWorld();
    }

    //alpha round
    public roundManager createRoundManager() {
        return  new alphaRoundManager();
    }
}
