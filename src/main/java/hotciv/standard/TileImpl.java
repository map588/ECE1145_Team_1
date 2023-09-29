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
        if(valid_terrain_type(type)) {
            this.terrain = terrainType.valueOf(type);
        }
    }

    public String getTypeString() {
        return terrain.toString();
    }




    //---------------------Validation methods---------------------//

    public static boolean valid_terrain_type(String type) {
        try {
            terrainType.valueOf(type);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

}
