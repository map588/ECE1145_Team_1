package hotciv.visual;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import hotciv.framework.*;
import hotciv.view.*;
import hotciv.stub.*;

/** Template code for exercise FRS 36.42.

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
public class ShowEndOfTurn {
  
  public static void main(String[] args) {
    Game game = new StubGame2();

    DrawingEditor editor = 
      new MiniDrawApplication( "Click top shield to end the turn",  
                               new HotCivFactory4(game) );
    editor.open();
    editor.showStatus("Click to shield to see Game's endOfTurn method being called.");

    // TODO: Replace the setting of the tool with your EndOfTurnTool implementation.
    //editor.setTool( new NullTool() );

    editor.setTool( new EndTurnTool(editor, game) );
  }
}


class EndTurnTool extends NullTool {
  private Game game;
  private DrawingEditor editor;

  public EndTurnTool(DrawingEditor e, Game g) {
    editor = e;
    game = g;
  }

  public void mouseDown(MouseEvent e, int x, int y) {
    String status;

    if (x > 559 && x < 587 && y > 64 && y < 103) {
      status = "Turn Ended.";
      game.endOfTurn();
    }
    else {
      status = "Click on Turn Shield to end turn.";
    }
    editor.showStatus(status);
  }
}


