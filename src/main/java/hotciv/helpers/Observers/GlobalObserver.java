package hotciv.helpers.Observers;

import hotciv.framework.*;
import hotciv.helper_Interfaces.Observer;
import hotciv.standard.*;


public class GlobalObserver implements Observer{
    private final UnitObserver unitObserver;
    private final CityObserver cityObserver;
    private final TileObserver tileObserver;
    private boolean globalEnabled;

    public GlobalObserver(GameImpl g){
        this.unitObserver = new UnitObserver(g);
        this.cityObserver = new CityObserver(g);
        this.tileObserver = new TileObserver(g);
    }


    public void update(GameImpl g) {
        if(!globalEnabled){
            return;
        }
        unitObserver.update(g);
        cityObserver.update(g);
        tileObserver.update(g);
    }

    public void setObserverEnabled(boolean observerEnabled) {
        this.globalEnabled = observerEnabled;
    }
}
