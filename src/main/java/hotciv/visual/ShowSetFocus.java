package hotciv.visual;

import hotciv.standard.GameImpl;
import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import hotciv.framework.*;
import hotciv.view.*;
import hotciv.stub.*;

import static hotciv.framework.GameType.*;

/** Template code for exercise FRS 36.40.

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Computer Science Department
     Aarhus University
   
   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
 */
public class ShowSetFocus {
  
  public static void main(String[] args) {
      Game game = new GameImpl(alphaCiv, 2);

    DrawingEditor editor = 
      new MiniDrawApplication( "Click any tile to set focus",  
                               new HotCivFactory4(game) );
    editor.open();
    editor.showStatus("Click a tile to see Game's setFocus method being called.");

    Position currentFocus;

    // TODO: Replace the setting of the tool with your SetFocusTool implementation.
    //editor.setTool( new SelectionTool(editor) );

    editor.setTool( new ShowSetFocusTool(editor, game) );
  }
}

class ShowSetFocusTool extends NullTool{

  private Game game;
  private DrawingEditor editor;
  static Position currentFocus;
  static Position focusPosition;
  static boolean selected;

  public ShowSetFocusTool(DrawingEditor e, Game g) {
    game = g;
    editor = e;
  }

  public void mouseDown(MouseEvent e, int x, int y) {
    selected = false;
    focusPosition = GfxConstants.getPositionFromXY(y, x);
    if(!focusPosition.equals(currentFocus)){
      editor.showStatus( "Tile focus " + focusPosition.getColumn() + ", " + focusPosition.getRow());
      game.setTileFocus(focusPosition);
    }
  }

  public void mouseDrag(MouseEvent e, int x, int y) {
    focusPosition = GfxConstants.getPositionFromXY(y, x);
    if(!focusPosition.equals(currentFocus)) {
      editor.showStatus( "Tile focus " + focusPosition.getColumn() + ", " + focusPosition.getRow());
      game.setTileFocus(focusPosition);
    }
  }

    public void mouseUp(MouseEvent e, int x, int y) {
      selected = true;
      if(!focusPosition.equals(currentFocus)) {
        currentFocus = focusPosition;
        editor.showStatus("Tile focus locked at " + currentFocus.getColumn() + ", " + currentFocus.getRow());
      }
    }
}