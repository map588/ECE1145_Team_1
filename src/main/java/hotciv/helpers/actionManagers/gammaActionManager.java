package hotciv.helpers.actionManagers;

import hotciv.helper_Interfaces.actionManager;
import hotciv.standard.GameImpl;
import hotciv.framework.Position;


public class gammaActionManager implements actionManager{

    @Override
    public void settlerAction(GameImpl g, Position p) {
        g.setCityAt(p, g.getUnitOwner(p));
        g.removeUnitAt(p);
    }

    @Override
    public void archerAction(GameImpl g, Position p) {
        g.getUnitAt(p).fortify();
    }
}
