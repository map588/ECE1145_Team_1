package hotciv.helpers.Observers;

import hotciv.framework.*;
import hotciv.helper_Interfaces.Observer;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;

public class TileObserver implements Observer{
    private TileImpl[][] tiles;
    private boolean enabled;

    public TileObserver(GameImpl g){
        this.tiles = g.getWorld().getTiles();
    }

    @Override
    public void update(GameImpl g) {
        TileImpl [][] temp = g.getWorld().getTiles();
        if(temp == tiles){
            return;
        }
        Player p = g.getPlayerInTurn();
        for(int i = 0; i < temp.length; i++){
            for(int j = 0; j < temp.length; j++){
                if(temp[i][j] != tiles[i][j])
                    System.out.println("Player " + p.toString() + "changed tile at position: " + i + ", " + j + " to " + temp[i][j].getTypeString() + '.');
            }
        }
    }

    @Override
    public void setObserverEnabled(boolean observerEnabled) {
        this.enabled = observerEnabled;
    }
}
