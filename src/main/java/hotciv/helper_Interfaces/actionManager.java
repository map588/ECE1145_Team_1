package hotciv.helper_Interfaces;

import hotciv.framework.Position;
import hotciv.standard.GameImpl;

public interface actionManager {

    default public void settlerAction(GameImpl g, Position p) {
    }

    default public void archerAction(GameImpl g, Position p) {
    }

    default public void legionAction(GameImpl g, Position p) {
    }
}
