package hotciv.visual;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import java.awt.event.*;
import javax.print.DocFlavor;
import javax.swing.*;

import hotciv.framework.*;
import hotciv.view.*;
import hotciv.stub.*;

/** Template code for exercise FRS 36.43.

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
public class ShowAction {
  
  public static void main(String[] args) {
    Game game = new StubGame2();

    DrawingEditor editor = 
      new MiniDrawApplication( "Shift-Click unit to invoke its action",  
                               new HotCivFactory4(game) );
    editor.open();
    editor.showStatus("Shift-Click on unit to see Game's performAction method being called.");

    // TODO: Replace the setting of the tool with your ActionTool implementation.
    //editor.setTool( new NullTool() );

    editor.setTool ( new ActionTool(editor, game) );

  }
}

class ActionTool extends NullTool {
  private Game game;
  private DrawingEditor editor;
  private boolean shiftdown;

  public ActionTool(DrawingEditor e, Game g) {
    editor = e;
    game = g;
  }

  @Override
  public void mouseDown(MouseEvent e, int x, int y) {

    String status = "Press shift to perform action.";

    if (((e.getModifiersEx() & InputEvent.SHIFT_DOWN_MASK) == InputEvent.SHIFT_DOWN_MASK) || shiftdown) {

      Position pos = GfxConstants.getPositionFromXY(x, y);
      if (game.getUnitAt(pos) != null) {
        game.performUnitActionAt(pos);
        status = "Action performed at: " + pos.getColumn() + ", " + pos.getRow();
      } else {
        status = "No unit at location.";
      }
    }
    editor.showStatus(status);
  }

  public boolean getShiftDown() {
    return shiftdown;
  }

  public void setShiftDown(boolean s) {
    shiftdown = true;
  }
}