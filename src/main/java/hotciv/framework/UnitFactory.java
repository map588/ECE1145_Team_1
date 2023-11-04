package hotciv.framework;

import hotciv.standard.*;
import hotciv.framework.*;

import static hotciv.framework.GameConstants.*;

public interface UnitFactory {
    default public UnitImpl createUnit(String type, Player owner){

        switch (type) {
            case ARCHER:
            case LEGION:
            case SETTLER:
                return new UnitImpl(type, owner);
            default:
                return null;
        }
    }
}
