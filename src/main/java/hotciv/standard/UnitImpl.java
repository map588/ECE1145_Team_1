package hotciv.standard;
import hotciv.framework.*;
import static hotciv.framework.GameConstants.*;


public class UnitImpl implements Unit {

    private enum type {
        archer, legion, settler, ufo
    }

    private type unitType;
    private Player owner;
    private int moveCount;
    private int defense;
    private int attack;
    private boolean isFortified;
    private boolean terrainTraversal;

    public UnitImpl(String unitType, Player owner) {
        if (valid_unit_type(unitType)) {
            this.unitType = type.valueOf(unitType);
            this.owner = owner;
            this.moveCount = unit_moveCount.get(unitType);
            this.defense = unit_defense.get(unitType);
            this.attack = unit_attack.get(unitType);
            this.terrainTraversal = unit_terrainTraversal.get(unitType);
            this.isFortified = false;
        }
    }

    public void increment_round(){
        this.moveCount = unit_moveCount.get(unitType.toString());
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
        return this.unitType.toString();
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


    public boolean getTerrainTraversal(){
        return this.terrainTraversal;
    }


    public void fortify(){
        if(this.unitType == type.archer){
            if(isFortified){
                this.defense = defense/2;
                isFortified = false;
            }
            else{
                this.defense = defense*2;
                setMoveCount(0);
                isFortified = true;
            }
        }
    }


    //---------------------Setters---------------------//


    public void setDefensiveStrength(int newDefensiveStr){
        this.defense = newDefensiveStr;
    }

    public void setAttackingStrength(int newAttackStr) {
        this.attack = newAttackStr;
    }

    public void setMoveCount(int numOfMoves){
        this.moveCount = numOfMoves;
    }

//-------- Validity check functions --------//


//Every type equality will need this annoying check, so I pulled it out into a method -MAP
public static boolean valid_unit_type(String unitType){
    try{
        type.valueOf(unitType);
    } catch (IllegalArgumentException e) {
        return false;
    }
        return true;
    }
}