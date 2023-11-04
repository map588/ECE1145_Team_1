package hotciv.helpers.actionManagers;

import static hotciv.framework.GameConstants.*;
import hotciv.framework.*;
import hotciv.standard.*;
import hotciv.helper_Interfaces.*;

import java.util.Objects;

public class thetaActionManager implements actionManager{

     public void customActions(GameImpl g, Position p, String unitType){

         //If we are in a custom action, and the unit is  a UFO
         if(Objects.equals(unitType, UFO)) {
             //IF there is a city at the position, remove a citizen
            if (g.getCityAt(p) != null) {
                //If that abduction reduces the population to 0, remove the city
                if (!g.getCityAt(p).decrementPopulation()) {
                    g.removeCityAt(p);
                }
            }
            //Else if there is not a city, but the tile is a FOREST, change to PLAIN
            else if(Objects.equals(g.getTileAt(p).getTypeString(), FOREST)){
                g.getTileAt(p).setTerrain(PLAINS);
            }
         }
     }



}
