package hotciv.standard;
import hotciv.framework.*;
import java.util.ArrayDeque;
import java.util.Arrays;

import static hotciv.framework.GameConstants.*;

public class AlphaCiv implements Game{
    private int numberOfPlayers;  //Local variable to hold a count of the number of players -TPD
    private int year;
    private ArrayDeque<Player> Players; //A deque will be helpful for cycling through the players
    private final Player firstPlayer;




    private TileImpl[][] world = new TileImpl[WORLDSIZE][WORLDSIZE];
    private CityImpl[][] cities = new CityImpl[WORLDSIZE][WORLDSIZE];
    private UnitImpl[][] units = new UnitImpl[WORLDSIZE][WORLDSIZE];

    private static boolean end_game = false;
    private Player winner = null;

    private GameImpl.GameType rules;


    //This constructor is currently specific to the first iteration checkoffs, but will be changed later -MAP
    public AlphaCiv() {   //Constructor for GameImpl
        this.numberOfPlayers = 2;
        this.Players = new ArrayDeque<Player>(numberOfPlayers);
        this.rules = GameImpl.GameType.gammaCiv;

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
        Position cityRED = new Position(1, 1);
        Position cityBLUE = new Position(1, 4);
        setCityAt(cityRED, Player.RED);
        setCityAt(cityBLUE, Player.BLUE);

        // to pass tests, RED starts with an archer and a Settler
        // BLUE starts with a Legion
        Position posArcher = new Position(0, 2);
        Position posSettler = new Position(3, 4);
        Position posLegion = new Position(2, 3);
        createUnitAt(posArcher, ARCHER, Player.RED);
        createUnitAt(posSettler, SETTLER, Player.RED);
        createUnitAt(posLegion, LEGION, Player.BLUE);


        this.year = -4000;
    }



    //----------------Getters-----------------//
    public int getNumberOfPlayers() {
        return this.numberOfPlayers;
    }



    public Tile getTileAt(Position p) {
        return world[p.getColumn()][p.getRow()];
    }



    //changed this to return a UnitImpl, doesn't seem to break anything, but wouldn't let me call it without it 9/27
    public UnitImpl getUnitAt(Position p) {
        return this.units[p.getColumn()][p.getRow()];
    }


    public City getCityAt(Position p) {
        return cities[p.getColumn()][p.getRow()];
    }


    //This will be changed later to account for the conditions needed to buy and place units -MAP
    public boolean createUnitAt(Position p, String unitType, Player owner) {
        if (this.units[p.getColumn()][p.getRow()] != null) {
            return false;
        }
        this.units[p.getColumn()][p.getRow()] = new UnitImpl(unitType, owner);
        return true;
    }


    public Player getPlayerInTurn() {
        return Players.peekFirst();
    }

    public Player getWinner() {
        Player first_place = null;

        if (this.getAge() >= -3000)
                    first_place = Player.RED;

        return first_place;
    }


    public int getAge() {
        return year;
    }


    //----------------Setters-----------------//

    public boolean setCityAt(Position p, Player owner) {
        this.cities[p.getColumn()][p.getRow()] = new CityImpl(owner);
        return true;
    }


    public void setAge(int i) {
        year = i;
    }


    public boolean moveUnit(Position from, Position to) {
        if (units[from.getColumn()][from.getRow()] != null && units[to.getColumn()][to.getRow()] == null) {
            units[to.getColumn()][to.getRow()] = units[from.getColumn()][from.getRow()];
            units[from.getColumn()][from.getRow()] = null;
            return true;
        }
        return false;
    }

    public void endOfTurn() {
        Players.addLast(Players.removeFirst());
        this.incrementAge(rules);
        if (Players.peekFirst() == firstPlayer) {
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


    public void changeWorkForceFocusInCityAt(Position p, String balance) {

    }

    public void changeProductionInCityAt(Position p, String unitType) {

    }

    public void performUnitActionAt(Position p) {
        if(this.rules != GameImpl.GameType.gammaCiv) {
            return;
        }
        String unit_type = getUnitAt(p).getTypeString();
        if (unit_type == SETTLER) {
            this.setCityAt(p, this.getUnitAt(p).getOwner());
        } else {
            int temp = getUnitAt(p).settlerAction();
        }
    }


    private void incrementAge() {
        int current = this.getAge();

                current += 100;
                this.setAge(current);

    }


    //function (temporary?) to perform attack between 2 positions.
    //Returns the unit that won (always the attacker for now).
    public Unit battle(Position attacker, Position defender) {
        return this.getUnitAt(attacker);
    }


    //----------------- True / False Queries ---------------------//

    public boolean isPlayerInGame(Player player) {
        return Players.contains(player);
    }

    public boolean isUnitAt(Position p) {
        return units[p.getColumn()][p.getRow()] != null;
    }

    public boolean isCityAt(Position p) {
        return cities[p.getColumn()][p.getRow()] != null;
    }

}
