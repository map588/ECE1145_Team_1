package hotciv.helpers.worldManagers;


import static hotciv.framework.GameConstants.*;
import hotciv.helper_Interfaces.worldManager;
import hotciv.framework.*;
import hotciv.standard.World;


public class alphaWorld implements worldManager {

        public alphaWorld() {}


        public void createWorld(World world) {

            for (int i = 0; i < WORLDSIZE; i++) {
                for (int j = 0; j < WORLDSIZE; j++) {

                    Position tiles = new Position(i,j);
                    world.getTileAt(tiles).setTerrain(PLAINS);
                }
            }

            Position ocean = new Position(1,0);
            Position hills = new Position(0,1);
            Position mountains = new Position(2,2);
            world.getTileAt(ocean).setTerrain(OCEANS);
            world.getTileAt(hills).setTerrain(HILLS);
            world.getTileAt(mountains).setTerrain(MOUNTAINS);
        }

}

