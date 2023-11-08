package hotciv.framework;

import hotciv.helper_Interfaces.*;
import hotciv.framework.GameType;

public interface ManagerFactory {

    public GameType getGameRules();
    public actionManager createActionManager();
    public attackManager createAttackManager();
    public ageManager    createAgeManager();
    public winnerManager createWinnerManager();
    public worldManager  createWorldManager();
    public roundManager  createRoundManager();
    public UnitFactory   createUnitFactory();
}
