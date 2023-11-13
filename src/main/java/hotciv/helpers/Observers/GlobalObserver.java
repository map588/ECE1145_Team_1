package hotciv.helpers.Observers;

import hotciv.framework.*;
import hotciv.helper_Interfaces.Observer;
import hotciv.standard.*;


public class GlobalObserver implements Observer{
    private  UnitObserver unitObserver;
    private  CityObserver cityObserver;
    private  TileObserver tileObserver;
    private boolean globalEnabled;

    public GlobalObserver(GameImpl g){
        this.unitObserver = new UnitObserver();
        this.cityObserver = new CityObserver();
        this.tileObserver = new TileObserver();
    }


    @Override
    public void update(GameImpl g) {
        if(!globalEnabled){
            return;
        }
        unitObserver.update(g);
        cityObserver.update(g);
        tileObserver.update(g);
    }

    @Override
    public void setObserverEnabled(boolean observerEnabled) {
        this.globalEnabled = observerEnabled;
    }
}
