package hotciv.standard;

import static hotciv.framework.GameConstants.WORLDSIZE;
import hotciv.framework.*;


public class World {

    private TileImpl[][] tiles;
    private CityImpl[][] cities;
    private UnitImpl[][] units;

    final int size;

    public World() {

        size = WORLDSIZE;

        tiles = new TileImpl[WORLDSIZE][WORLDSIZE];
        cities = new CityImpl[WORLDSIZE][WORLDSIZE];
        units = new UnitImpl[WORLDSIZE][WORLDSIZE];

        for (int i = 0; i < WORLDSIZE; i++) {
            for (int j = 0; j < WORLDSIZE; j++) {
                tiles[i][j] = new TileImpl();
                cities[i][j] = null;
                units[i][j] = null;
            }
        }
    }

    public World(int alternateSize) {
        size = alternateSize;

        tiles = new TileImpl[size][size];
        cities = new CityImpl[size][size];
        units = new UnitImpl[size][size];

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
        if(p.getColumn() < 0 || p.getColumn() >= size || p.getRow() < 0 || p.getRow() >= size) {
            return null;
        }
        return tiles[p.getColumn()][p.getRow()];
    }

    public UnitImpl getUnitAt(Position p) {
        if(p.getColumn() < 0 || p.getColumn() >= size || p.getRow() < 0 || p.getRow() >= size) {
            return null;
        }
        return units[p.getColumn()][p.getRow()];
    }

    public CityImpl getCityAt(Position p) {
        if(p.getColumn() < 0 || p.getColumn() >= size || p.getRow() < 0 || p.getRow() >= size) {
            return null;
        }
        return cities[p.getColumn()][p.getRow()];
    }

    public UnitImpl[][] getUnits() {
        return units;
    }

    public CityImpl[][] getCities() {
        return cities;
    }

    public TileImpl[][] getTiles() {
        return tiles;
    }

    //-------------Setters---------------//
    public void setTileAt(Position p, String terrain) {
        if(p.getColumn() < 0 || p.getColumn() >= size || p.getRow() < 0 || p.getRow() >= size) {
            return;
        }
        tiles[p.getColumn()][p.getRow()].setTerrain(terrain);
    }

    public void makeUnitAt(Position p, String unitType, Player owner, UnitFactory unitFactory) {
        if(p.getColumn() < 0 || p.getColumn() >= size || p.getRow() < 0 || p.getRow() >= size) {
            return;
        }
        units[p.getColumn()][p.getRow()] = unitFactory.createUnit(unitType, owner);
    }

    public void setCityAt(Position p, Player owner) {
        if(p.getColumn() < 0 || p.getColumn() >= size || p.getRow() < 0 || p.getRow() >= size) {
            return;
        }
        cities[p.getColumn()][p.getRow()] = new CityImpl(owner, p);
    }


    //-------------Destructors---------------//
    public void removeUnitAt(Position p) {
        units[p.getColumn()][p.getRow()] = null;
    }

    public void removeCityAt(Position p) {
        cities[p.getColumn()][p.getRow()] = null;
    }



    public void moveUnitTo(Position from, Position to) {
        if(to.getColumn() < 0 || to.getColumn() >= size || to.getRow() < 0 || to.getRow() >= size) {
            throw new IllegalArgumentException("Position out of bounds: " + to);
        }
        if(from.getColumn() < 0 || from.getColumn() >= size || from.getRow() < 0 || from.getRow() >= size) {
            throw new IllegalArgumentException("Position out of bounds: " + from);
        }
        if(units[from.getColumn()][from.getRow()] == null) {
            throw new IllegalArgumentException("No unit at position: " + from);
        }
        units[to.getColumn()][to.getRow()] = units[from.getColumn()][from.getRow()];
        units[from.getColumn()][from.getRow()] = null;

    }

}