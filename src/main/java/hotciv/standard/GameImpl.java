package hotciv.standard;

import hotciv.framework.*;
import hotciv.helper_Interfaces.*;
import hotciv.helpers.ageManagers.*;
import hotciv.helpers.actionManagers.*;
import hotciv.helpers.winnerManagers.*;
import hotciv.helpers.worldManagers.*;
import hotciv.helpers.attackManagers.*;
import hotciv.helpers.roundManagers.*;
import hotciv.factories.*;

import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.Arrays;

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

    private ManagerFactory manager_factory;

    private ageManager age_manager;
    private winnerManager winner_manager;
    private worldManager world_manager;
    private actionManager action_manager;
    public attackManager attack_manager;
    private roundManager round_manager;


    public GameImpl(GameType ruleSet, int numPlayers) {

        this.numberOfPlayers = numPlayers;
        this.numberSuccessfulAttacks = new int[this.numberOfPlayers];

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


        this.age = this.age_manager.START_AGE;
        this.world = world_manager.createWorld();
    }


    public void endOfTurn() {
        //winnerManager.checkForWinner(this, Players.peekFirst()); TODO: implement checkForWinner
        Players.addLast(Players.removeFirst());  //rotate
        if (Players.peekFirst() == firstPlayer) {
            this.endOfRound();
        }
    }

    private void endOfRound() {
        age_manager.incrementAge(this);
        round_manager.incrementRound(this);
    }

    public void changeWorkForceFocusInCityAt(Position p, String balance) {
    }

    public void changeProductionInCityAt(Position p, String unitType) {
    }

    //Move this variability stuff to a strategy file like actionManager -10/25
    public void performUnitActionAt(Position p) {
        String unitType = getUnitAt(p).getTypeString();

        switch (unitType) {
            case SETTLER:
                action_manager.settlerAction(this, p);
                break;
            case ARCHER:
                action_manager.archerAction(this, p);
                break;
            case LEGION:
                action_manager.legionAction(this, p);
                break;
            default:
                //default do nothing
                break;
        }
    }

    public boolean attack(Position attacker, Position defender) {
        //numberSuccessfulAttacks[getUnitOwner(attacker).ordinal()]++; //increments attackSuccessful array for each winning attack
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

    public City getCityAt(Position p) {
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

    public int[] getNumberOfSuccessfulAttacks() {
        return this.numberSuccessfulAttacks;
    }

    public int getDefensiveStrength(Unit unit) {
        return unit.getDefensiveStrength();
    }

    public int getAttackStrength(Unit unit) {
        return unit.getAttackingStrength();
    }

    public GameType getVersion() {
        return this.version;
    }

    //---------------------Setters--------------------------------//
    //This will be changed later to account for the conditions needed to buy and place units -MAP
    public boolean setCityAt(Position p, Player owner) {
        this.world.setCityAt(p, owner);
        return true;
    }

    public void createUnitAt(Position p, String unit, Player owner) {
        this.world.setUnitAt(p, unit, owner);
    }

    public boolean moveUnit(Position from, Position to) {
        if (this.getUnitAt(from) != null && this.getUnitAt(to) == null && this.getUnitAt(from).getOwner() == this.getPlayerInTurn()) {
            this.world.moveUnitTo(from, to);
            return true;
        }
        return false;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void incrementNumberOfSuccessfulAttacks(Position p) {
        numberSuccessfulAttacks[getUnitOwner(p).ordinal()]++;
    }

    public int getRoundNumber(){
        return roundNumber;
    }

    //---------------------Destructors-----------------------------//

    public void removeUnitAt(Position position) {
        this.world.removeUnitAt(position);
    }

    //----------------- Queries ---------------------//

    public boolean isPlayerInGame(Player player) {
        return this.Players.contains(player);
    }

    public boolean isUnitAt(Position p) {
        return this.getUnitAt(p) != null;
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
        roundNumber = n + 1;
    }

    public attackManager getAttack_manager(){
        return this.attack_manager;
    }
}
