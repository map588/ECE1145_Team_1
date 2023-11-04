package hotciv.object_factories;

import hotciv.framework.Player;
import hotciv.framework.UnitFactory;
import hotciv.standard.UnitImpl;

import static hotciv.framework.GameConstants.*;

public class thetaUnitFactory implements UnitFactory {

    @Override
    public UnitImpl createUnit(String type, Player owner){

        switch (type) {
            case ARCHER:
            case LEGION:
            case SETTLER:
            case UFO:
                return new UnitImpl(type,owner);
            default:
                return null;
        }
    }
}
