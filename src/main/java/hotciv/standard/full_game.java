package hotciv.standard;

import hotciv.framework.*;
import hotciv.framework.GameType;
import hotciv.helpers.*;
import hotciv.helper_Interfaces.*;
import hotciv.manager_factories.*;
import hotciv.object_factories.*;
import hotciv.visual.HotCivFactory4;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Factory;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.NullTool;

public class full_game {

    GameImpl game;


    public full_game(GameType version, int numberOfPlayers) {
        GameImpl game = new GameImpl(version, numberOfPlayers);
        HotCivFactory4 factory = new HotCivFactory4(game);
        DrawingEditor editor = new MiniDrawApplication("Click top shield to end the turn",
                factory);
        editor.open();
        editor.showStatus("Click to shield to see Game's endOfTurn method being called.");
        editor.setTool(new NullTool());
    }


}
