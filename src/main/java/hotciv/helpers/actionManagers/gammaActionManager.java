package hotciv.helpers.actionManagers;

import hotciv.helper_Interfaces.actionManager;
import hotciv.standard.GameImpl;
import hotciv.framework.Position;


public class gammaActionManager implements actionManager{

    @Override
    public void settlerAction(GameImpl currentGame, Position p) {
        currentGame.setCityAt(p, currentGame.getUnitOwner(p));
        currentGame.removeUnitAt(p);
    }

    @Override
    public void archerAction(GameImpl currentGame, Position p) {
        currentGame.getUnitAt(p).fortify();
    }
}
