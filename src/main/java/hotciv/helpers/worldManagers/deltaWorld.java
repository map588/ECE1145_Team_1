package hotciv.helpers.worldManagers;

import hotciv.framework.Position;
import hotciv.helper_Interfaces.worldManager;
import static hotciv.framework.GameConstants.*;
import hotciv.standard.World;
import hotciv.framework.Player;


public class deltaWorld implements worldManager{

    //We will pass in only the world object, unless a later implementation requires more information.
    @Override
    public void createWorld(World world) {

        String type;
        String[] layout =
                new String[] {
                        "...ooMooooo.....",
                        "..ohhoooofffoo..",
                        ".oooooMooo...oo.",
                        ".ooMMMoooo..oooo",
                        "...ofooohhoooo..",
                        ".ofoofooooohhoo.",
                        "...ooo..........",
                        ".ooooo.ooohooM..",
                        ".ooooo.oohooof..",
                        "offfoooo.offoooo",
                        "oooooooo...ooooo",
                        ".ooMMMoooo......",
                        "..ooooooffoooo..",
                        "....ooooooooo...",
                        "..ooohhoo.......",
                        ".....ooooooooo..",
                };

        // Conversion
        String line;
        for (int r = 0; r < WORLDSIZE; r++) {
            line = layout[r];
            for (int c = 0; c < WORLDSIZE; c++) {
                char tileChar = line.charAt(c);
                type = "error";
                if(tileChar == '.') {type = OCEANS;}
                if(tileChar == 'o') {type = PLAINS;}
                if(tileChar == 'M') {type = MOUNTAINS;}
                if(tileChar == 'f') {type = FOREST;}
                if(tileChar == 'h') {type = HILLS;}
                Position p = new Position(c, r);
                world.getTileAt(p).setTerrain(type);
            }
        }

        Position cityRed = new Position(8,12);
        Position cityBlue = new Position(4,5);
        world.setCityAt(cityRed, Player.RED);
        world.setCityAt(cityBlue, Player.BLUE);
    }


}
