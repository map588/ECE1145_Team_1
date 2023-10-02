package hotciv.framework;

import static hotciv.framework.GameConstants.*;


public class CreateWorld {

    Game game;

    /**
     * Constructor will handle which map creation function will need to be run
     */
    public CreateWorld(Game g) {
        game = g;

        if (g.getRules() == GameType.deltaCiv)
            deltaCivWorld();
        else
            defaultWorld();
    }


    private void defaultWorld() {

        for (int i = 0; i < WORLDSIZE; i++) {
            for (int j = 0; j < WORLDSIZE; j++) {

                Position tiles = new Position(i,j);
                game.getTileAt(tiles).setTerrain(PLAINS);
            }
        }

        Position ocean = new Position(1,0);
        Position hills = new Position(0,1);
        Position mountains = new Position(2,2);
        game.getTileAt(ocean).setTerrain(OCEANS);
        game.getTileAt(hills).setTerrain(HILLS);
        game.getTileAt(mountains).setTerrain(MOUNTAINS);
    }

    private void deltaCivWorld() {

        // ALGORITHM FROM StubGame1.java IN CANVAS

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
                game.getTileAt(p).setTerrain(type);
            }
        }
    }
}