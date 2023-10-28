package hotciv.standard;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Position;



public class CityImpl implements City {

    private Player owner;
    private Position city_position;
    private int production_rate;
    private int treasury;
    private int population;
    private int growth_rate;

    private String current_production_type;
    private String workforce_focus;

    public CityImpl(Player owner, Position position) {
        this.owner = owner;
        this.city_position = position;
        production_rate = 6; //temp
        treasury = 0;
        population = 1;  //temp
        growth_rate = 0; //temp
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
        return this.population;
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
        return this.treasury;
    }

    /**
     * return the type of unit this city is currently producing.
     *
     * @return a string type defining the unit under production,
     * see GameConstants for valid values.
     */
    public String getProduction() {
        return this.current_production_type;
    }

    /**
     * @return the production rate of the city
     */
    public int getProductionRate() {
        return this.production_rate;
    }

    /**
     * @return the growth rate of the city
     */
    public int getGrowthRate() {
        return this.growth_rate;
    }

    /**
     * @param p player to set as owner
     * @param game game to check if player is in
     */
    public void setOwner(Player p, GameImpl game) {
        if(game.isPlayerInGame(p))
            this.owner = p;
        else
            throw new IllegalArgumentException("Player not in game");
    }

    /**
     * return the work force's focus in this city.
     *
     * @return a string type defining the focus, see GameConstants
     * for valid return values.
     */
    public String getWorkforceFocus() {
        return this.workforce_focus;
    }

    // --------------------- Mutator methods ------------------------------

    /**
     * change the production of this city.
     *
     * @param unitType a string type defining the unit under production,
     *                 see GameConstants for valid values.
     */
    public void changeProductionInCity(String unitType) {
        if (valid_production_type(unitType)) {
            this.current_production_type = unitType;
        }
    }

    /**
     * change the focus of this city.
     *
     * @param balance a string type defining the focus, see GameConstants
     *                for valid return values.
     */
    public void changeWorkForceFocusInCity(String balance) {
        if (valid_focus_type(balance)){
            this.workforce_focus = balance;
        }
    }

    public void increment_round(GameImpl g) {
        int current_production_cost = GameConstants.unit_cost.get(this.current_production_type);
        this.treasury  += this.production_rate;
        this.population += this.growth_rate;

        if (this.treasury >= current_production_cost){
            this.treasury -= current_production_cost;
            Position new_unit_position = g.findProductionPosition(this.city_position);
            g.createUnitAt(new_unit_position, this.current_production_type, this.owner);
        }
    }

    // --------------------- Private validity checks ------------------------------
    private enum focusType {
        production, food
    }

    public static boolean valid_focus_type(String type) {
        try {
            CityImpl.focusType.valueOf(type);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    private boolean valid_production_type(String unitType) {
        return UnitImpl.valid_unit_type(unitType);
    }

}