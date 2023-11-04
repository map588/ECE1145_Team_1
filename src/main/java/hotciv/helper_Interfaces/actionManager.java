package hotciv.helper_Interfaces;

import hotciv.framework.Position;
import hotciv.standard.GameImpl;

import static hotciv.framework.GameConstants.*;

public interface actionManager {

    default public void unitActionAt(GameImpl g, Position p) {
        String unitType = g.getUnitAt(p).getTypeString();

        switch (unitType) {
            case SETTLER:
                this.settlerAction(g, p);
                break;
            case ARCHER:
                this.archerAction(g, p);
                break;
            case LEGION:
                this.legionAction(g, p);
                break;
            default:
                this.customActions(g, p, unitType);
                break;
        }
    }

    default public void settlerAction(GameImpl g, Position p){}

    default public void archerAction(GameImpl g, Position p){}

    default public void legionAction(GameImpl g, Position p){}

    default public void customActions(GameImpl g, Position p, String unitType){}

}
