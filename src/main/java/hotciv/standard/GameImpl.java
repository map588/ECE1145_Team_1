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

    private final int numberOfPlayers;
    private ArrayDeque<Player> Players;
    private final Player firstPlayer;
    public World world;
    private int age;
    private GameType version;

    private ageManager age_manager;
    private winnerManager winner_manager;
    private worldManager world_manager;
    private actionManager action_manager;



    public GameImpl(GameType version, int numPlayers) {   //Constructor for GameImpl
        this.numberOfPlayers = numPlayers;
        this.Players = new ArrayDeque<Player>(numberOfPlayers);

        Players.addAll(Arrays.asList(Player.values()).subList(0, numberOfPlayers));
        this.firstPlayer = Players.peekFirst();

        this.version = version;

        this.world = new World();

        this.age = -4000;

        switch(version){
            case alphaCiv:
                this.world_manager  = new alphaWorld();
                this.age_manager    = new alphaAgeManager();
                this.winner_manager = new alphaWinnerManager();
                this.action_manager = new alphaActionManager();

                break;
            case betaCiv:
                this.world_manager  = new alphaWorld();
                this.age_manager    = new betaAgeManager();
                this.winner_manager = new betaWinnerManager();
                this.action_manager = new betaActionManager();

                break;
            case gammaCiv:
                this.world_manager  = new gammaWorld();
                this.age_manager    = new gammaAgeManager();
                this.winner_manager = new gammaWinnerManager();
                this.action_manager = new gammaActionManager();

                break;
            case deltaCiv:
                this.world_manager  = new deltaWorld();
                this.age_manager    = new deltaAgeManager();
                this.winner_manager = new alphaWinnerManager();
                this.action_manager = new deltaActionManager();

                break;
            default:

                break;
        }

        world_manager.createWorld(world);
    }

  //This will be changed later to account for the conditions needed to buy and place units -MAP
  public boolean createUnitAt( Position p, String unitType, Player owner ) {
        if(world.getUnitAt(p) != null) {
            return false;
        }
        world.setUnitAt(p, unitType, owner);
    return true;
  }

  public void removeUnitAt(Position position){ world.removeUnitAt(position); }

  public boolean moveUnit( Position from, Position to ) {
    if(world.getUnitAt(from) != null && world.getUnitAt(to) == null) {
        world.moveUnitTo(from, to);
        return true;
    }
    return false;
  }

  public void endOfTurn() {
      Players.addLast(Players.removeFirst());  //rotate
      this.age = this.age_manager.incrementAge(this);
      if (Players.peekFirst() == firstPlayer) {
          this.endOfRound();
      }
  }

  private void endOfRound() {
        Position p;
      for (int i = 0; i < WORLDSIZE; i++) {
          for (int j = 0; j < WORLDSIZE; j++) {
              p = new Position(i,j);
              if (world.getCityAt(p) != null) {
                  world.getCityAt(p).increment_round();
              }
          }
      }
  }

  public void changeWorkForceFocusInCityAt( Position p, String balance ) {
  }

  public void changeProductionInCityAt( Position p, String unitType ) {
  }

  public void performUnitActionAt( Position unitPosition ) {
    String unitType = getUnitAt(unitPosition).getTypeString();
    switch(unitType) {
        case SETTLER:
            action_manager.settlerAction(this, unitPosition);
            break;
        case ARCHER:
            action_manager.archerAction(this, unitPosition);
            break;
        case LEGION:
            action_manager.legionAction(this, unitPosition);
            break;
    }
  }

  public Unit battle(Position attacker, Position defender){
        return this.getUnitAt(attacker);
  }


  //---------------------- Getters -----------------------------//
  public int getNumberOfPlayers(){ return this.numberOfPlayers; }

  public Tile getTileAt( Position p ) {
      return world.getTileAt(p);
  }

  public UnitImpl getUnitAt( Position p ) {
      return world.getUnitAt(p);
  }
  public City getCityAt( Position p ) {
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

  public Player getUnitOwner(Position unitPosition) {
      return this.getUnitAt(unitPosition).getOwner();
  }


  //---------------------Setters--------------------------------//
  //This will be changed later to account for the conditions needed to buy and place units -MAP
  public boolean setCityAt( Position cityPosition, Player owner ) {
      world.setCityAt(cityPosition, owner);
      return true;
  }

  //----------------- True / False Queries ---------------------//

  public boolean isPlayerInGame(Player player) {
      return Players.contains(player);
  }

  public GameType getVersion() {
      return version;
  }

    /**
     * @param i allow age to be changed by modifier
     */
  public void setAge(int i) {
      age = i;
  }
}
