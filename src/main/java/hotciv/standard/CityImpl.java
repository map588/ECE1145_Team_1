package hotciv.standard;
import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;

public class CityImpl implements City{

   private Player owner;
   private int production_rate;
   private int treasury;
   private int population;

   private String current_production;
   private String workforce_focus;

    public CityImpl(Player owner){
        this.owner = owner;
        int production_rate = 6;
        int treasury = 0;
        int population = 1;
    }

    /**
     * return the owner of this city.
     *
     * @return the player that controls this city.
     */
    public Player getOwner() {
        return this.owner;
    }

    /**
     * return the size of the population.
     *
     * @return population size.
     */
    public int getSize() {
        return 1;           //This is for the naive implementation, will be changed later -MAP
    }

    /**
     * return the treasury, i.e. the
     * number of 'money'/production in the
     * city's treasury which can be used to
     * produce a unit in this city
     *
     * @return an integer, the amount of production
     * in the city treasury
     */
    public int getTreasury() {
        return 0;
    }

    /**
     * return the type of unit this city is currently producing.
     *
     * @return a string type defining the unit under production,
     * see GameConstants for valid values.
     */
    public String getProduction() {
        return null;
    }
    /**
     * return the work force's focus in this city.
     *
     * @return a string type defining the focus, see GameConstants
     * for valid return values.
     */
    public String getWorkforceFocus() {
        return null;
    }

    /**
     * change the production of this city.
     *
     * @param unitType a string type defining the unit under production,
     * see GameConstants for valid values.
     */
    public void changeProductionInCity(String unitType) {
        private enum unitType {
            archer, legion, settler, workboat, caravel
        }
    }

    /**
     * change the focus of this city.
     *
     * @param balance a string type defining the focus, see GameConstants
     * for valid return values.
     */
    public void changeWorkForceFocusInCity(String balance) {

    }
}
