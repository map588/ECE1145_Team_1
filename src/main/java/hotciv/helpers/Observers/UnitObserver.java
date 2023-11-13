package hotciv.helpers.Observers;

import hotciv.framework.*;
import hotciv.helper_Interfaces.Observer;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;
import hotciv.standard.World;


public class UnitObserver implements Observer{
    private UnitImpl[][] units;
    boolean enabled;

    public UnitObserver(GameImpl g, Position position){
        this.units = g.getWorld().getUnits();
    }

    public void update(GameImpl g) {
        if(!enabled){
            return;
        }
        UnitImpl [][] newUnits = g.getWorld().getUnits();
        Player p = g.getPlayerInTurn();
        for(int i = 0; i < newUnits.length; i++){
            for(int j = 0; j < newUnits.length; j++){
                if(newUnits[i][j] != units[i][j])
                    if(units[i][j] == null)
                        System.out.println("Player " + p.toString() + "created unit at position: " + i + ", " + j + " of type " + newUnits[i][j].getTypeString() + '.');
                    else if(newUnits[i][j] == null && units[i][j] != null)
                        System.out.println("Player " + p.toString() + "'s "+ units[i][j].getTypeString() + " was killed at position: " + i + ", " + j + " by a " + newUnits[i][j].getTypeString() + '.');
                    else if(newUnits[i][j] != null && units[i][j] == null)
                    System.out.println("Player " + p.toString() + "moved unit to position: " + i + ", " + j + " to " + newUnits[i][j].getTypeString() + '.');
            }
        }
    }

    public void setObserverEnabled(boolean observerEnabled) {
        this.enabled = observerEnabled;
    }
}
