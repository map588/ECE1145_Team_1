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
import static java.lang.Math.abs;

/** Test the TextFigure to display age in
 * the status panel.
 * 
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
public class ShowText {
  
  public static void main(String[] args) {

    Game game = new GameImpl(alphaCiv, 2);
    TextFigure tf;

    DrawingEditor editor = 
      new MiniDrawApplication( "Click to see age text change...",  
                               new HotCivFactory3(game) );
    editor.open();

    /*
    TextFigure tf = new TextFigure("4000 BC",
                                   new Point(GfxConstants.AGE_TEXT_X,
                                             GfxConstants.AGE_TEXT_Y) );

    */

    //editor.drawing().add(tf);
    //editor.setTool( new ChangeAgeTool(tf) );
    editor.setTool( new ChangeAgeTool(editor, game));
  }
}

// A test stub ChangeAgeTool. Use it as template/idea for your
// real implementation that reads the age from Game.
/*
class ChangeAgeTool extends NullTool {
  private TextFigure textFigure;
  public ChangeAgeTool(TextFigure tf) {
    textFigure = tf;
  }
  int count = 0;
  public void mouseDown(MouseEvent e, int x, int y) {
    count++;
    textFigure.setText( ""+(4000-count*100)+" BC" );
  }
}
 */

class ChangeAgeTool extends NullTool{
  private Game game;
  private DrawingEditor editor;
  private TextFigure textFigure;

  public ChangeAgeTool(DrawingEditor e, Game g) {
    editor = e;
    game = g;


  }

  public void mouseDown(MouseEvent e, int x, int y) {

    game.endOfTurn();

    if (game.getAge() < 0) {
      String AgeText = String.format("%d BC", abs(game.getAge()));
      textFigure = new TextFigure(AgeText, new Point(GfxConstants.AGE_TEXT_X, GfxConstants.AGE_TEXT_Y) );
      editor.showStatus( "Age displayed.");
    }
    else {
      String AgeText = String.format("%d AD", abs(game.getAge()));
      textFigure = new TextFigure(AgeText, new Point(GfxConstants.AGE_TEXT_X, GfxConstants.AGE_TEXT_Y));
      editor.showStatus( "Age displayed.");
    }
    textFigure.draw(editor.view().getGraphics());
  }

}

