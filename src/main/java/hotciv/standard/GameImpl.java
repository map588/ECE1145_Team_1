package hotciv.standard;

import hotciv.framework.*;


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

    private int numberOfPlayers;  //Local variable to hold a count of the number of players -TPD

    private int year;

    private ArrayDeque<Player> Players; //A deque will be helpful for cycling through the players

    private final Player firstPlayer;

    private TileImpl[][] world  = new TileImpl[WORLDSIZE][WORLDSIZE];
    private CityImpl[][] cities  = new CityImpl[WORLDSIZE][WORLDSIZE];
    private UnitImpl[][] units  = new UnitImpl[WORLDSIZE][WORLDSIZE];

    private static boolean winner_found = false;
    private Player winner = null;

    private GameType rules;



    //This constructor is currently specific to the first iteration checkoffs, but will be changed later -MAP
    public GameImpl(GameType r) {   //Constructor for GameImpl
        this.numberOfPlayers = 2;
        this.Players = new ArrayDeque<Player>(numberOfPlayers);
        this.rules = r;

        //This line looks gross but IntelliJ was complaining when I used a for loop
        //It populates the Players queue in order depending on the number of players
        Players.addAll(Arrays.asList(Player.values()).subList(0, numberOfPlayers));

        this.firstPlayer = Players.peekFirst();

        //Default constructor makes PLAINS tiles
        for (int i = 0; i < WORLDSIZE; i++)
            for (int j = 0; j < WORLDSIZE; j++)
                world[i][j] = new TileImpl();

        for (int i = 0; i < WORLDSIZE; i++)
            for (int j = 0; j < WORLDSIZE; j++)
                units[i][j] = null;

        for (int i = 0; i < WORLDSIZE; i++)
            for (int j = 0; j < WORLDSIZE; j++)
                cities[i][j] = null;

        //set the special tiles
        world[1][0].setTerrain(OCEANS);
        world[0][1].setTerrain(HILLS);
        world[2][2].setTerrain(MOUNTAINS);

        // to pass tests, start with a city for red and blue
        Position cityRED = new Position(1,1);
        Position cityBLUE = new Position(1,4);
        setCityAt(cityRED, Player.RED);
        setCityAt(cityBLUE, Player.BLUE);

        // to pass tests, RED starts with an archer and a Settler
        // BLUE starts with a Legion
        Position posArcher = new Position(0,2);
        Position posSettler = new Position(3, 4);
        Position posLegion = new Position(2,3);
        createUnitAt(posArcher, ARCHER, Player.RED);
        createUnitAt(posSettler, SETTLER, Player.RED);
        createUnitAt(posLegion, LEGION, Player.BLUE);


        this.year = -4000;
 }

  public int getNumberOfPlayers(){ return this.numberOfPlayers; }

  public Tile getTileAt( Position p ) {
    return world[p.getColumn()][p.getRow()];
  }

  //changed this to return a UnitImpl, doesn't seem to break anything, but wouldn't let me call it without it 9/27
  public UnitImpl getUnitAt( Position p ) {
    return this.units[p.getColumn()][p.getRow()];
  }

  //This will be changed later to account for the conditions needed to buy and place units -MAP
  public boolean createUnitAt( Position p, String unitType, Player owner ) {
        if(this.units[p.getColumn()][p.getRow()] != null) {
            return false;
        }
        this.units[p.getColumn()][p.getRow()] = new UnitImpl(unitType, owner);
        return true;
  }

  public City getCityAt( Position p ) {
    return cities[p.getColumn()][p.getRow()];
  }

  //Same as above, will be changed later -MAP
  public boolean setCityAt( Position p, Player owner ) {
    this.cities[p.getColumn()][p.getRow()] = new CityImpl(owner);
    return true;
  }

  public Player getPlayerInTurn() {
    return Players.peekFirst();
  }

  public Player getWinner() {
    Winner winning_player = new Winner(this); // constructor will assign a winner
    return Winner.returnWinner();
    }

  public int getAge() {
    return year;
  }

  public void setAge(int i) {
    year = i;
  }

  public boolean moveUnit( Position from, Position to ) {
    if(units[from.getColumn()][from.getRow()] != null && units[to.getColumn()][to.getRow()] == null) {
        units[to.getColumn()][to.getRow()] = units[from.getColumn()][from.getRow()];
        units[from.getColumn()][from.getRow()] = null;
        return true;
    }
    return false;
  }
    public void endOfTurn() {
        Players.addLast(Players.removeFirst());  //rotate
        ManageAge manager = new ManageAge(this); // constructor will increase game age as necessary
        if( Players.peekFirst() == firstPlayer ) {
            this.updateCityValues();
        }
    }

  private void updateCityValues() {
      for (int i = 0; i < WORLDSIZE; i++) {
          for (int j = 0; j < WORLDSIZE; j++) {
              if (cities[i][j] != null) {
                  cities[i][j].increment_round();
              }
          }
      }
  }



  public void changeWorkForceFocusInCityAt( Position p, String balance ) {

  }
  public void changeProductionInCityAt( Position p, String unitType ) {

  }
  public void performUnitActionAt( Position p ) {
    String unit_type = getUnitAt(p).getTypeString();
    if(unit_type == SETTLER){
        if(rules == GameType.gammaCiv){
            this.setCityAt(p, this.getUnitAt(p).getOwner());
        }
        else {
            int temp = getUnitAt(p).settlerAction(rules);
        }
    }

  }

    //function (temporary?) to perform attack between 2 positions.
    //Returns the unit that won (always the attacker for now).
  public Unit battle(Position attacker, Position defender){
        return this.getUnitAt(attacker);
  }

  //----------------- True / False Queries ---------------------//

    public boolean isPlayerInGame(Player player) {
        return Players.contains(player);
    }

    public GameType getRules() {
        return rules;
    }


    public boolean returnWinnerFound() {
        return winner_found;
    }

    public void setWinnerFound(boolean b) {
        winner_found = b;
    }
}
