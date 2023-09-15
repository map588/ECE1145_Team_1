package hotciv.standard;
import hotciv.framework.Tile;

import java.util.Objects;

public class TileImpl implements Tile {

    //enums are a great way to represent a fixed set of values -MAP
    private enum terrainType {
        plains, ocean, forest, hills, mountain
    }

    private terrainType terrain;


    public TileImpl(){
        this.terrain = terrainType.plains;
    }

    public void setTerrain(String type) {
        try{
            this.terrain = terrainType.valueOf(type); //If the salami lid won't fit, throw exception -MAP
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid tile type: " + type);
        }
    }

    public String getTypeString() {
        return terrain.toString();
    }

}
