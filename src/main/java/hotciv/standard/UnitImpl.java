package hotciv.standard;
import hotciv.framework.*;


public class UnitImpl implements Unit {




    private enum unitType {
        archer, legion, settler
    }

    private unitType type;
    private Player owner;
    private int moveCount;
    private int defense;
    private int attack;


    public UnitImpl(String type, Player owner) {
        if (valid_unit_type(type)) {
            this.type = unitType.valueOf(type);
            this.owner = owner;
            this.moveCount = 1;
            this.defense = 0;
            this.attack = 0;
        }
    }

    /**
     * return the type of the unit
     *
     * @return unit type as a string, valid values are at
     * least those listed in GameConstants, particular variants
     * may define more strings to be valid.
     */

    //---------------------Getters---------------------//
    public String getTypeString() {
        return this.type.toString();
    }

    /**
     * return the owner of this unit.
     *
     * @return the player that controls this unit.
     */
    public Player getOwner() {
        return this.owner;
    }

    /**
     * return the move distance left (move count).
     * A move count of N means the unit may travel
     * a distance of N in this turn. The move count
     * is reset to the units maximal value before
     * a new turn starts.
     *
     * @return the move count
     */
    public int getMoveCount() {
        return this.moveCount;
    }

    /**
     * return the defensive strength of this unit
     *
     * @return defensive strength
     */
    public int getDefensiveStrength() {
        return this.defense;
    }

    /**
     * return the attack strength of this unit
     *
     * @return attack strength
     */
    public int getAttackingStrength() {
        return this.attack;
    }

    /**
     * action performed of a settler unit
     *
     * @return 0 if no action performed(alphaciv)
     */
    public int settlerAction(GameType rules) {
        if(rules == GameType.gammaCiv){
            //TODO: settler performs build city and turns into a city. owner remains the same
            return 1;
        }
        else
            return 0;

    }


    /**
     * action performed of an archer unit
     *
     * @return 0 if no action (alphaciv)
     */
    public Integer archerAction(GameType rules) {
        if(rules == GameType.gammaCiv){ //archer performs fortify
            //TODO: archer does fortify action. doubles defense but cannot move. if already fortified, it is undone
            return 1;
        }
        else
            return 0;
    }

    //---------------------Setters---------------------//




//-------- Validity check functions --------//


//Every type equality will need this annoying check so I pulled it out into a method -MAP
public static boolean valid_unit_type(String type){
    try{
        unitType.valueOf(type);
    } catch (IllegalArgumentException e) {
        return false;
    }
        return true;
    }

}