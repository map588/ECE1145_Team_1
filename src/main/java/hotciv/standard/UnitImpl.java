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


    public UnitImpl(String type, Player owner){
        try{
            this.type = unitType.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid unit type: " + type);
        }
        this.owner = owner;
    }

    /**
     * return the type of the unit
     *
     * @return unit type as a string, valid values are at
     * least those listed in GameConstants, particular variants
     * may define more strings to be valid.
     */
    public String getTypeString() {
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
