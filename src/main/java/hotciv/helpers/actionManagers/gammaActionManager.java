package hotciv.helpers.actionManagers;

import hotciv.helper_Interfaces.actionManager;
import hotciv.standard.GameImpl;
import hotciv.framework.Position;
public class gammaActionManager implements actionManager{

    public void settlerAction(GameImpl currentGame, Position settlerPosition) {
        currentGame.setCityAt(settlerPosition, currentGame.getUnitOwner(settlerPosition));
        currentGame.removeUnitAt(settlerPosition);
    }

    public void archerAction(GameImpl currentGame, Position archerPosition) {
        currentGame.getUnitAt(archerPosition).fortify();
    }
}
