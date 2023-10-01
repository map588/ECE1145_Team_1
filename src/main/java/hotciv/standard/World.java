package hotciv.standard;

import static hotciv.framework.GameConstants.WORLDSIZE;
import hotciv.framework.*;


public class World {

    private TileImpl[][] tiles;
    private CityImpl[][] cities;
    private UnitImpl[][] units;

    private final int size;

    public World() {

        size = WORLDSIZE;

        tiles  = new TileImpl[WORLDSIZE][WORLDSIZE];
        cities  = new CityImpl[WORLDSIZE][WORLDSIZE];
        units  = new UnitImpl[WORLDSIZE][WORLDSIZE];

        for (int i = 0; i < WORLDSIZE; i++) {
            for (int j = 0; j < WORLDSIZE; j++) {
                tiles[i][j] = new TileImpl();
                cities[i][j] = null;
                units[i][j] = null;
            }
        }
    }

    public World(int alternateSize){
        size = alternateSize;

        tiles  = new TileImpl[size][size];
        cities  = new CityImpl[size][size];
        units  = new UnitImpl[size][size];

        for (int i = 0; i < alternateSize; i++) {
            for (int j = 0; j < alternateSize; j++) {
                tiles[i][j] = new TileImpl();
                cities[i][j] = null;
                units[i][j] = null;
            }
        }
    }


    //-------------Getters---------------//
    public TileImpl getTileAt(Position p) {
        return tiles[p.getColumn()][p.getRow()];
    }

    public UnitImpl getUnitAt(Position p) {
        return units[p.getColumn()][p.getRow()];
    }

    public CityImpl getCityAt(Position p) {
        return cities[p.getColumn()][p.getRow()];
    }



    //-------------Setters---------------//
    public void setTileAt(Position p, String terrain) {
        tiles[p.getColumn()][p.getRow()].setTerrain(terrain);
    }

    public void setUnitAt(Position p, String unitType, Player owner) {
        units[p.getColumn()][p.getRow()] = new UnitImpl(unitType, owner);
    }

    public void setCityAt(Position p, Player owner) {
        cities[p.getColumn()][p.getRow()] = new CityImpl(owner);
    }


    //-------------Destructors---------------//
    public void removeUnitAt(Position p) {
        units[p.getColumn()][p.getRow()] = null;
    }

    public void removeCityAt(Position p) {
        cities[p.getColumn()][p.getRow()] = null;
    }

    public void removeTileAt(Position p) {
        tiles[p.getColumn()][p.getRow()] = null;
    }

}
