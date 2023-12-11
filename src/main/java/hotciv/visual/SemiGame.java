package hotciv.visual;

import hotciv.framework.*;
import hotciv.framework.GameType;
import hotciv.standard.GameImpl;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;


public class SemiGame {

    public static void main(String[] args) {
        Game game = new GameImpl(GameType.semiCiv, 2);

        DrawingEditor editor =
                new MiniDrawApplication("Click to play", new HotCivFactory4(game));

        editor.open();
        editor.showStatus("Click and drag any item to see Game's proper response.");

        editor.setTool( new CompTool(editor, game));
    }
}
