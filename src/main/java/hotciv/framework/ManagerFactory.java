package hotciv.framework;

import hotciv.helper_Interfaces.*;

public interface ManagerFactory {
    public actionManager createActionManager();
    public attackManager createAttackManager();
    public ageManager    createAgeManager();
    public winnerManager createWinnerManager();
    public worldManager  createWorldManager();
    public roundManager  createRoundManager();
    public UnitFactory   createUnitFactory();
}
