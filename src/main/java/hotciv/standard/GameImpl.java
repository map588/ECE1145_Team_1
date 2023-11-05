package hotciv.standard;

import hotciv.framework.*;
import hotciv.helper_Interfaces.*;
import hotciv.manager_factories.*;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

import static hotciv.framework.GameConstants.*;

//hotfix 1
/** Skeleton implementation of HotCiv.
 
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/

public class GameImpl implements Game {

    private int numberOfPlayers;
    private Player firstPlayer;
    private ArrayDeque<Player> Players;
    private World world;
    private int age;
    private GameType version;
    private int[] numberSuccessfulAttacks;
    private int roundNumber;
    private Player winner;

    private ManagerFactory manager_factory;

    //I am going to assign these in the manager factory
    public UnitFactory unit_factory;

    private ageManager age_manager;
    private winnerManager winner_manager;
    private worldManager world_manager;
    private actionManager action_manager;
    public  attackManager attack_manager;
    private roundManager round_manager;


    public GameImpl(GameType ruleSet, int numPlayers) {

        this.numberOfPlayers = numPlayers;
        this.numberSuccessfulAttacks = new int[this.numberOfPlayers];
        for(int i = 0; i < numPlayers; i++){this.numberSuccessfulAttacks[i] = 0;}
        this.Players = new ArrayDeque<Player>(this.numberOfPlayers);
        this.Players.addAll(Arrays.asList(Player.values()).subList(0, this.numberOfPlayers));
        this.firstPlayer = this.Players.peekFirst();

        this.version = ruleSet;

        switch (version) {
            case alphaCiv:
                this.manager_factory = new alphaManagerFactory();
                break;

            case betaCiv:
                this.manager_factory = new betaManagerFactory();
                break;

            case gammaCiv:
                this.manager_factory = new gammaManagerFactory();
                break;

            case deltaCiv:
                this.manager_factory = new deltaManagerFactory();
                break;

            case epsilonCiv:
                this.manager_factory = new epsilonManagerFactory();
                break;

            case zetaCiv:
                this.manager_factory = new zetaManagerFactory();
                break;

            case semiCiv:
                this.manager_factory = new semiManagerFactory();
                break;

            default:
                this.manager_factory = new alphaManagerFactory();
                break;
        }

        this.world_manager = manager_factory.createWorldManager();
        this.age_manager = manager_factory.createAgeManager();
        this.winner_manager = manager_factory.createWinnerManager();
        this.action_manager = manager_factory.createActionManager();
        this.attack_manager = manager_factory.createAttackManager();
        this.round_manager = manager_factory.createRoundManager();
        this.unit_factory = manager_factory.createUnitFactory();


        this.age = this.age_manager.START_AGE;
        this.world = world_manager.createWorld(this);
    }


    public void endOfTurn() {
        Players.addLast(Players.removeFirst());  //rotate
        if (Players.peekFirst() == firstPlayer) {
            this.endOfRound();
        }
    }

    private void endOfRound() {
        Player possibleWinner = this.getWinner();
        if(possibleWinner != null){
            this.winner = possibleWinner;
            System.out.println("Game Over, "+ this.winner.toString() +" won at round: " + this.roundNumber);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press Enter to continue...");

            scanner.nextLine(); // Pause until enter is pressed

            System.out.println("Continuing execution.");
            scanner.close();
            System.exit(0);
        }
        age_manager.incrementAge(this);
        round_manager.incrementRound(this);
    }

    /**
     *
     * @param p       the position of the city whose focus
     *                should be changed.
     * @param balance a string defining the focus of the work
     *                force in a city. Valid values are at least
     *                GameConstants.productionFocus and
     *                GameConstants.foodFocus.
     *
     */
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        this.getCityAt(p).changeWorkForceFocusInCity(balance);
    }

    public void changeProductionInCityAt(Position p, String unitType) {
        this.getCityAt(p).changeProductionInCity(unitType);
    }

    //Move this variability stuff to a strategy file like actionManager -10/25
    public void performUnitActionAt(Position p) {
        action_manager.unitActionAt(this, p);
    }

    public boolean attack(Position attacker, Position defender) {
        return this.attack_manager.attack(attacker, defender, this);
    }

    //---------------------- Getters -----------------------------//
    public int getNumberOfPlayers() {
        return this.numberOfPlayers;
    }

    public Tile getTileAt(Position p) {
        return this.world.getTileAt(p);
    }

    public UnitImpl getUnitAt(Position p) {
        return this.world.getUnitAt(p);
    }

    public CityImpl getCityAt(Position p) {
        return this.world.getCityAt(p);
    }

    public Player getPlayerInTurn() {
        return this.Players.peekFirst();
    }

    public Player getWinner() {
        return this.winner_manager.getWinner(this);
    }

    public int getAge() {
        return this.age;
    }

    public Player getUnitOwner(Position p) {
        return this.getUnitAt(p).getOwner();
    }

    public int getRoundNumber(){
        return roundNumber;
    }

    public int[] getNumberOfSuccessfulAttacks() {
        return this.numberSuccessfulAttacks;
    }

    public int getDefensiveStrength(Unit unit) {
        return unit.getDefensiveStrength();
    }

    public int getAttackStrength(Unit unit) {
        return unit.getAttackingStrength();
    }

    public int getWorldSize() {
        return this.world.size;
    }

    public GameType getVersion() {
        return this.version;
    }

    //---------------------Setters--------------------------------//
    public boolean setCityAt(Position p, Player owner) {
        this.world.setCityAt(p, owner);
        return true;
    }

    public void createUnitAt(Position p, String unit, Player owner) {
        this.world.makeUnitAt(p, unit, owner, this.unit_factory);
    }

    /**
     * Moves a unit from one position to another, checks if the position has an enemy
     * and attacks if it does.
     * @param from the position that the unit has now
     * @param to   the position the unit should move to
     * @return
     */
    public boolean moveUnit(Position from, Position to) {
        UnitImpl unit_toMove = this.getUnitAt(from);
        UnitImpl unit_onTile = this.getUnitAt(to);
        String terrain = this.getTileAt(to).getTypeString();

        boolean unit_exists = unit_toMove != null;
        boolean destination_is_empty = unit_onTile == null;
        boolean is_within_one_tile = isWithinOneTile(from, to);
        boolean unit_belongs_to_player = unit_toMove.getOwner() == this.getPlayerInTurn();
        boolean isTraversable = (unit_toMove.getTerrainTraversal() || (terrain != MOUNTAINS  && terrain != OCEANS));
        boolean isWithinBounds = (to.getColumn() >= 0 && to.getColumn() < world.size && to.getRow() >= 0 && to.getRow() < world.size);


        if (unit_exists            &&
            is_within_one_tile     &&
            unit_belongs_to_player &&
            isTraversable          &&
            isWithinBounds) {
            //If the tile is occuplied by a friendly unit, return false
            if(!destination_is_empty && unit_onTile.getOwner() == unit_toMove.getOwner()){return false;}

            if (unit_toMove.decrementMoveCount()) {

                if(destination_is_empty) {
                    this.world.moveUnitTo(from, to);
                    return true;
                }
                else if(this.attack(from, to)){
                    this.world.moveUnitTo(from, to);
                    return true;
                }

                return false;
            }
        }

        return false;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void incrementNumberOfSuccessfulAttacks(Position p) {
        numberSuccessfulAttacks[getUnitOwner(p).ordinal()]++;
    }

    public void changeCityOwner(Position p, Player player) {
        this.getCityAt(p).setOwner(player);
    }

    public void changeTileType(Position p, String terrain) {
        this.world.setTileAt(p, terrain);
    }
    //---------------------Destructors-----------------------------//

    public void removeUnitAt(Position position) {
        this.world.removeUnitAt(position);
    }
    public void removeCityAt(Position position) {
        this.world.removeCityAt(position);
    }

    //----------------- Queries ---------------------//

    public boolean isPlayerInGame(Player player) {
        return this.Players.contains(player);
    }

    public boolean isUnitAt(Position p) {
        return this.getUnitAt(p) != null;
    }

    public boolean isWithinOneTile(Position from, Position to) {
        return Math.abs(from.getColumn() - to.getColumn()) <= 1 &&
                Math.abs(from.getRow() - to.getRow()) <= 1;
    }


    public Position findProductionPosition(Position p) {

        class int_pair {
            public int x;
            public int y;

            public int_pair(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        //Defining order of checked positions for unit placement around city
        int_pair[] surrounding_positions = new int_pair[9];
        surrounding_positions[0] = new int_pair(0, 0);
        surrounding_positions[1] = new int_pair(0, -1);
        surrounding_positions[2] = new int_pair(1, -1);
        surrounding_positions[3] = new int_pair(1, 0);
        surrounding_positions[4] = new int_pair(1, 1);
        surrounding_positions[5] = new int_pair(0, 1);
        surrounding_positions[6] = new int_pair(-1, 1);
        surrounding_positions[7] = new int_pair(-1, 0);
        surrounding_positions[8] = new int_pair(-1, -1);

        //Checking each position around city for empty space
        for (int i = 0; i < 9; i++) {
            Position new_position = new Position(p.getColumn() + surrounding_positions[i].x, p.getRow() + surrounding_positions[i].y);
            if (getUnitAt(new_position) == null) {
                return new_position;
            }
        }
        return null;
    }

//-------------------Testing-Only Functions----------------------//

    public void progressRounds(int n) {
        for (int i = 0; i < (n * numberOfPlayers); i++) {
            this.endOfTurn();
        }
        roundNumber += n;
    }

    public attackManager getAttack_manager(){
        return this.attack_manager;
    }


    public void forceUnitTo(Position from, Position to){
        this.world.moveUnitTo(from, to);
    }

    public void toggleWinnerManager(boolean enable){
        if(!enable){
        this.winner_manager = new winnerManager() {
            @Override
            public Player getWinner(GameImpl game) {
                return null;
            }
        };
    }
        else{
            this.winner_manager = manager_factory.createWinnerManager();
        }
    }
}
