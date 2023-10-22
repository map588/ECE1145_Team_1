package hotciv.standard;

import hotciv.framework.*;
import hotciv.helper_Interfaces.*;
import hotciv.helpers.AgingStrategies.*;
import hotciv.helpers.actionManagers.*;
import hotciv.helpers.winnerManagers.*;
import hotciv.helpers.worldManagers.*;


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

    private static int numberOfPlayers;
    private static Player firstPlayer;
    private ArrayDeque<Player> Players;
    public World world;
    private static int age;
    private static GameType version;

    private static ageManager age_manager;
    private static winnerManager winner_manager;
    private static worldManager world_manager;
    private static actionManager action_manager;


    public GameImpl(GameType ruleSet, int numPlayers) {   //Constructor for GameImpl
        numberOfPlayers = numPlayers;

        this.Players = new ArrayDeque<Player>(numberOfPlayers);
        Players.addAll(Arrays.asList(Player.values()).subList(0, numberOfPlayers));

        firstPlayer = Players.peekFirst();

        version = ruleSet;

        this.world = new World();

        age = -4000;

        switch (version) {
            case alphaCiv:
                world_manager  = new alphaWorld();
                age_manager    = new alphaAgeManager();
                winner_manager = new alphaWinnerManager();
                action_manager = new alphaActionManager();

                break;
            case betaCiv:
                world_manager  = new alphaWorld();
                age_manager    = new betaAgeManager();
                winner_manager = new betaWinnerManager();
                action_manager = new betaActionManager();

                break;
            case gammaCiv:
                world_manager  = new gammaWorld();
                age_manager    = new gammaAgeManager();
                winner_manager = new gammaWinnerManager();
                action_manager = new gammaActionManager();

                break;
            case deltaCiv:
                world_manager  = new deltaWorld();
                age_manager    = new deltaAgeManager();
                winner_manager = new alphaWinnerManager();
                action_manager = new deltaActionManager();

                break;
            default:

                break;
        }

        world_manager.createWorld(world);
    }

    //This will be changed later to account for the conditions needed to buy and place units -MAP
    public boolean createUnitAt(Position p, String unitType, Player owner) {
        if (world.getUnitAt(p) != null) {
            return false;
        }
        world.setUnitAt(p, unitType, owner);
        return true;
    }

    public void removeUnitAt(Position position) {
        world.removeUnitAt(position);
    }

    public boolean moveUnit(Position from, Position to) {
        if (world.getUnitAt(from) != null && world.getUnitAt(to) == null) {
            world.moveUnitTo(from, to);
            return true;
        }
        return false;
    }

    public void endOfTurn() {
        Players.addLast(Players.removeFirst());  //rotate
        age = age_manager.incrementAge(this);
        if (Players.peekFirst() == firstPlayer) {
            this.endOfRound();
        }
    }

    private void endOfRound() {
        Position p;
        for (int i = 0; i < WORLDSIZE; i++) {
            for (int j = 0; j < WORLDSIZE; j++) {
                p = new Position(i, j);
                if (world.getCityAt(p) != null) {
                    world.getCityAt(p).increment_round();
                }
            }
        }
    }

    public void changeWorkForceFocusInCityAt(Position p, String balance) {
    }

    public void changeProductionInCityAt(Position p, String unitType) {
    }

    public void performUnitActionAt(Position p) {
        String unitType = getUnitAt(p).getTypeString();

        //pass read-only copy for safety
        final GameImpl game = this;
        switch (unitType) {
            case SETTLER:
                action_manager.settlerAction(game, p);
                break;
            case ARCHER:
                action_manager.archerAction(game, p);
                break;
            case LEGION:
                action_manager.legionAction(game, p);
                break;
            default:
                //default do nothing
                break;
        }
    }

    public Unit battle(Position attacker, Position defender) {
        return this.getUnitAt(attacker);
    }


    //---------------------- Getters -----------------------------//
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public Tile getTileAt(Position p) {
        return world.getTileAt(p);
    }

    public UnitImpl getUnitAt(Position p) {
        return world.getUnitAt(p);
    }

    public City getCityAt(Position p) {
        return world.getCityAt(p);
    }

    public Player getPlayerInTurn() {
        return Players.peekFirst();
    }

    public Player getWinner() {
        return winner_manager.getWinner(this);
    }

    public int getAge() {
        return age;
    }

    public Player getUnitOwner(Position p) {
        return this.getUnitAt(p).getOwner();
    }


    //---------------------Setters--------------------------------//
    //This will be changed later to account for the conditions needed to buy and place units -MAP
    public boolean setCityAt(Position p, Player owner) {
        world.setCityAt(p, owner);
        return true;
    }

    //----------------- True / False Queries ---------------------//

    public boolean isPlayerInGame(Player player) {
        return Players.contains(player);
    }

    public GameType getVersion() {
        return version;
    }
}
