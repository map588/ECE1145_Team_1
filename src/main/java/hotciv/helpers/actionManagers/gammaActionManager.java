package hotciv.helpers.actionManagers;

import hotciv.helper_Interfaces.actionManager;
import hotciv.standard.GameImpl;
import hotciv.framework.Position;
public class gammaActionManager implements actionManager{

    public int settlerAction(GameImpl g, Position p) {
        g.setCityAt(p, g.getUnitAt(p).getOwner());
        g.removeUnitAt(p);
        return 1;
    }

    public void archerAction(GameImpl g, Position p) {
        g.getUnitAt(p).fortify();
    }
}
