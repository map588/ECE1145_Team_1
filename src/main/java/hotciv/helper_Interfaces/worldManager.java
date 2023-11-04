package hotciv.helper_Interfaces;

import hotciv.framework.*;
import static hotciv.framework.GameConstants.*;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.World;
import hotciv.framework.Tile;

public interface worldManager {

    default World createWorld(GameImpl g) {
        World world = new World();

        Position ocean = new Position(1, 0);
        Position hills = new Position(0, 1);
        Position mountains = new Position(2, 2);
        world.getTileAt(ocean).setTerrain(OCEANS);
        world.getTileAt(hills).setTerrain(HILLS);
        world.getTileAt(mountains).setTerrain(MOUNTAINS);

        Position cityRED = new Position(1,1);
        Position cityBLUE = new Position(1,4);
        world.setCityAt(cityRED, Player.RED);
        world.setCityAt(cityBLUE, Player.BLUE);


        Position posArcher = new Position(0,2);
        Position posSettler = new Position(3, 4);
        Position posLegion = new Position(2,3);
        world.makeUnitAt(posArcher,  ARCHER, Player.RED, g.unit_factory);
        world.makeUnitAt(posSettler, SETTLER, Player.RED, g.unit_factory);
        world.makeUnitAt(posLegion,  LEGION, Player.BLUE, g.unit_factory);

        return world;
    }

}

