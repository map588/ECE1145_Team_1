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