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


    //Every type equality will need this annoying check so I pulled it out into a method -MAP
    private boolean set_unit_type(String type){
        try{
            this.type = unitType.valueOf(type);
            return true;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid unit type: " + type);
        }
    }

    public UnitImpl(String type, Player owner){
        if(this.set_unit_type(type)){
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
    public String getTypeString() {
        if (type == unitType.archer)
            return "archer";
        else if (type == unitType.legion)
            return "legion";
        else if (type == unitType.settler)
            return "settler";
        else
            return null;
    }

    /**
     * return the owner of this unit.
     *
     * @return the player that controls this unit.
     */
    public Player getOwner() {
        return null;
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
        return 0;
    }

    /**
     * return the defensive strength of this unit
     *
     * @return defensive strength
     */
    public int getDefensiveStrength() {
        return 0;
    }

    /**
     * return the attack strength of this unit
     *
     * @return attack strength
     */
    public int getAttackingStrength() {
        return 0;
    }
}
