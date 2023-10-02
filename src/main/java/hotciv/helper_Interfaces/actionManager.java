package hotciv.helper_Interfaces;

import hotciv.framework.Position;
import hotciv.standard.GameImpl;

public interface actionManager {

    default public int settlerAction(GameImpl g, Position p) {
        return 0; //returns only used for testing purposes so far
    }

    default public void archerAction(GameImpl g, Position p) {
    }
}
