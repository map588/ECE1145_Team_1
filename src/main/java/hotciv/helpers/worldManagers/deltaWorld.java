package hotciv.helpers.worldManagers;

import hotciv.framework.Position;
import hotciv.helper_Interfaces.worldManager;
import static hotciv.framework.GameConstants.*;
import hotciv.standard.World;


public class deltaWorld implements worldManager{

    public deltaWorld(){}

    //We will pass in only the world object, unless a later implementation requires more information.
    public World createWorld(World world) {

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
                w.getTileAt(p).setTerrain(type);
            }
        }
    }


}
