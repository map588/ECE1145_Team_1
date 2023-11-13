package hotciv.helpers.Observers;

import hotciv.framework.*;
import hotciv.helper_Interfaces.Observer;
import hotciv.standard.GameImpl;
import hotciv.standard.CityImpl;

import java.util.Objects;

public class CityObserver implements Observer{
    private CityImpl [][] cities;
    private boolean enabled;

    public CityObserver(GameImpl g){
        this.cities = g.getWorld().getCities();
    }


    @Override
    public void update(GameImpl g) {
        CityImpl [][] newCities = g.getWorld().getCities();
        if(newCities == cities){
            return;
        }
        Player p = g.getPlayerInTurn();
        for(int i = 0; i < newCities.length; i++){
            for(int j = 0; j < newCities.length; j++) {
                boolean prev_exists = cities[i][j]   != null;
                boolean new_exists = newCities[i][j] != null;

                boolean cityRemains = prev_exists && new_exists;
                boolean cityExists =  prev_exists || new_exists;

                String  prev_owner = prev_exists ? cities[i][j].getOwner().toString() : "null";
                String  new_owner =  new_exists ? newCities[i][j].getOwner().toString() : "null";
                if (cityExists){
                        if (prev_exists && !new_exists)
                            System.out.println("Player " + cities[i][j].getOwner() + "'s city at position: " + i + ", " + j + " was destroyed.");
                        if (cityRemains && !Objects.equals(prev_owner, new_owner))
                            System.out.println("Player " + cities[i][j].getOwner() + "'s city at position: " + i + ", " + j + " was conquered by " + newCities[i][j].getOwner() + '.');
                        if (!prev_exists && new_exists)
                            System.out.println("Player " + newCities[i][j].getOwner() + " created a city at position: " + i + ", " + j + '.');
                        if (cities[i][j].getTreasury() != newCities[i][j].getTreasury())
                            System.out.println("Player " + newCities[i][j].getOwner() + "'s city at position: " + i + ", " + j + " treasury changed from " + cities[i][j].getTreasury() + " to " + newCities[i][j].getTreasury() + '.');
                        if (cities[i][j].getSize() != newCities[i][j].getSize())
                            System.out.println("Player " + newCities[i][j].getOwner() + "'s city at position: " + i + ", " + j + " size changed from " + cities[i][j].getSize() + " to " + newCities[i][j].getSize() + '.');
                        if (cities[i][j].getProduction() != newCities[i][j].getProduction())
                            System.out.println("Player " + newCities[i][j].getOwner() + "'s city at position: " + i + ", " + j + " production changed from " + cities[i][j].getProduction() + " to " + newCities[i][j].getProduction() + '.');
                        if (cities[i][j].getWorkforceFocus() != newCities[i][j].getWorkforceFocus())
                            System.out.println("Player " + newCities[i][j].getOwner() + "'s city at position: " + i + ", " + j + " workforce focus changed from " + cities[i][j].getWorkforceFocus() + " to " + newCities[i][j].getWorkforceFocus() + '.');
                }
            }
        }

    }


    public void setObserverEnabled(boolean observerEnabled) {
        this.enabled = observerEnabled;
    }
}
