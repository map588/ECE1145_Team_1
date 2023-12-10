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

import hotciv.view.GfxConstants;

import static hotciv.framework.GameType.*;

/** Template code for exercise FRS 36.39.

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
public class ShowMove {



  public static void main(String[] args) {
    Game game = new StubGame2();


    DrawingEditor editor =
      new MiniDrawApplication( "Move any unit using the mouse",
                               new HotCivFactory4(game) );
    editor.open();
    editor.showStatus("Move units to see Game's moveUnit method being called.");

    // TODO: Replace the setting of the tool with your UnitMoveTool implementation.
    //editor.setTool( new SelectionTool(editor) );

    // TRE26
     editor.setTool( new UnitMoveTool(editor, game) );
  }
}


class UnitMoveTool extends NullTool {
  private Game game;
  private DrawingEditor editor;

  Position start, end;
  Position previous;
  Position next;

  public UnitMoveTool(DrawingEditor d, Game g) {
    editor = d;
    game = g;
  }

  @Override
  public void mouseDown(MouseEvent e, int x, int y) {
    start = GfxConstants.getPositionFromXY(x, y);
    previous = start;
    String status = String.format("Mouse Down at position %d, %d release to confirm location.", start.getColumn(), start.getRow() );
    editor.showStatus(status);
  }

  @Override
  public void mouseDrag(MouseEvent e, int x, int y){
    next = GfxConstants.getPositionFromXY(x, y);
    String status = String.format("Mouse Drag at position %d, %d release to confirm location.", start.getColumn(), start.getRow());
    editor.showStatus(status);
    game.moveUnit(previous, next);
    previous = next;
  }


  @Override
  public void mouseUp(MouseEvent e, int x, int y) {
    end = GfxConstants.getPositionFromXY(x, y);

    if(game.getUnitAt(end) != null)
        game.moveUnit(end, start);

    if (game.getUnitAt(start) != null) {
      boolean successfulMove = game.moveUnit(start, end);
        String status;
        if(successfulMove){
            status = String.format("Mouse Up at position %d, %d from start position %d, %d. Move Successful.", end.getColumn(), end.getRow(), start.getColumn(), start.getRow());
        }
      else {
            status = String.format("Mouse Up at position %d, %d from start position %d, %d. Move Failed.", end.getColumn(), end.getRow(), start.getColumn(), start.getRow());
        }
        editor.showStatus(status);
    }
    else {
      String status = String.format("Mouse Up at position %d, %d from start position %d, %d. No unit to move.",end.getColumn(),end.getRow(), start.getColumn(), start.getRow());
      editor.showStatus(status);
    }
  }
}
