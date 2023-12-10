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

import static hotciv.view.GfxConstants.*;

/** Template code for exercise FRS 36.44.

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
public class ShowComposition {
  
  public static void main(String[] args) {
    Game game = new GameImpl(GameType.deltaCiv, 2);

    DrawingEditor editor = 
      new MiniDrawApplication( "Click and/or drag any item to see all game actions",  
                               new HotCivFactory4(game) );
    editor.open();
    editor.showStatus("Click and drag any item to see Game's proper response.");

    // TODO: Replace the setting of the tool with your CompositionTool implementation.
    //editor.setTool( new NullTool() );

     editor.setTool( new CompTool(editor, game) );
  }
}

enum TileContents { UNIT, CITY, TILE, SHEILD, NOTHING };

class CompTool extends NullTool {

  private Game game;
  private DrawingEditor editor;

  private UnitMoveTool unitMoveTool;
  private EndTurnTool endTurnTool;
  private ActionTool actionTool;
  private ChangeCityTool changeCityTool;
  private ShowSetFocusTool setFocusTool;
  private NullTool nullTool;

  private Tool tool;
  private String status;


  TileContents contents;

  public CompTool(DrawingEditor e, Game g) {
    editor = e;
    game = g;
    unitMoveTool = new UnitMoveTool(editor, game);
    endTurnTool = new EndTurnTool(editor, game);
    actionTool = new ActionTool(editor, game);
    //changeCityTool = new ChangeCityTool(editor, game);
    setFocusTool = new ShowSetFocusTool(editor, game);
  }


  @Override
  public void mouseDown(MouseEvent e, int x, int y) {
      updateTool(e, x, y);
      editor.showStatus(status);

      if(tool != null)
        tool.mouseDown(e, x, y);

    // ChangeAgeTool.mouseDown(e, x, y);
    //showWorldTool ?
  }

  public void mouseDrag(MouseEvent e, int x, int y) {
    editor.showStatus(status);

    if(tool != null)
        tool.mouseDrag(e, x, y);


//    unitMoveTool.mouseDrag(e, x, y);
//    endTurnTool.mouseDrag(e, x, y);
//    actionTool.mouseDrag(e, x, y);
    //changeCityTool.mouseDrag(e, x, y);
//    setFocusTool.mouseDrag(e, x, y);
    // ChangeAgeTool.mouseDrag(e, x, y);
    //showWorldTool ?
  }

  public void mouseUp(MouseEvent e, int x, int y) {
        contents = getTileContents(x,y);
//    switch (contents) {
//        case UNIT:
//          if ((e.getModifiersEx() & InputEvent.SHIFT_DOWN_MASK) == InputEvent.SHIFT_DOWN_MASK) {
//            tool = actionTool;
//            status = "action tool";
//          } else {
//            tool = unitMoveTool;
//            status = "unit move tool";
//          }
//            break;
//        case CITY:
//            tool = actionTool;
//            status = "action tool";
//            break;
//        case TILE:
//            tool = setFocusTool;
//            status = "focus tool";
//            break;
//        case SHEILD:
//            tool = endTurnTool;
//            status = "end turn tool";
//            break;
//        case NOTHING:
//            tool = nullTool;
//            status = "focus tool";
//            break;
//        default:
//            tool = nullTool;
//            status = "focus tool";
//            break;
//        }
        editor.showStatus(status);
        if(tool != null)
            tool.mouseUp(e, x, y);

//    unitMoveTool.mouseUp(e, x, y);
//    endTurnTool.mouseUp(e, x, y);
//    actionTool.mouseUp(e, x, y);
    //changeCityTool.mouseUp(e, x, y);
//    setFocusTool.mouseUp(e, x, y);
    // ChangeAgeTool.mouseUp(e, x, y);
    //showWorldTool ?
  }

  private TileContents getTileContents(int x , int y) {
    Position pos = getPositionFromXY(x,y);

    if (game.getUnitAt(pos) != null) {
      return TileContents.UNIT;
    }
    else if (game.getCityAt(pos) != null) {
      return TileContents.CITY;
    }
    else if (game.getTileAt(pos) != null) {
      return TileContents.TILE;
    }
    else if (x > 559 && x < 587 && y > 64 && y < 103) {
      return TileContents.SHEILD;
    }
    else {
      return TileContents.NOTHING;
    }
  }

  private void updateTool(MouseEvent e, int x ,int y) {

      switch (getTileContents(x,y)) {
          case UNIT:
              if ((e.getModifiersEx() & InputEvent.SHIFT_DOWN_MASK) == InputEvent.SHIFT_DOWN_MASK) {
                  tool = actionTool;
                  status = "action tool";
                } else {
                  tool = unitMoveTool;
                  status = "unit move tool";
                }
              break;
          case CITY:
              tool = actionTool;
              status = "action tool";
              break;
          case TILE:
              tool = setFocusTool;
              status = "focus tool";
              break;
          case SHEILD:
              tool = endTurnTool;
              status = "end turn tool";
              break;
          default:
              tool = nullTool;
              status = "focus tool";
              break;
      }
  }

}
