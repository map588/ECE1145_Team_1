package hotciv.helper_Interfaces;

import hotciv.framework.*;
import static hotciv.framework.GameConstants.*;
import hotciv.framework.Position;
import hotciv.standard.World;
import hotciv.framework.Tile;

public interface worldManager {

    default public World createWorld(World world) {
        deltaCivWorld();
    }

}