package hotciv.visual;


import hotciv.framework.*;
import hotciv.helper_Interfaces.*;

import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import minidraw.standard.*;
import minidraw.framework.*;
import hotciv.framework.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import hotciv.framework.*;
import hotciv.view.*;
import hotciv.stub.*;

/** Test the CityFigure.
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
public class ShowCity {
  
  public static void main(String[] args) {

    Game game = new StubGame1();

    DrawingEditor editor = 
      new MiniDrawApplication( "Click to see city graphics update...",  
                               new HotCivFactory3(game) );
    editor.open();

    editor.setTool( new ChangeCityTool(editor, game) );

  }
}

class ChangeCityTool extends NullTool {

  private DrawingEditor editor;
  private Game game;
  private City city;
  private CityFigure cityFigure;

  public ChangeCityTool(DrawingEditor e, Game g) {
    game = g;
    editor = e;
  }

  public void mouseDown(MouseEvent e, int x, int y) {
    Position pos = GfxConstants.getPositionFromXY(x,y);
    game.changeProductionInCityAt(pos, "legion");
    cityFigure.changed();
  }
}

class HotCivFactory3 implements Factory {
  private Game game;
  public HotCivFactory3(Game g) { game = g; }

  public DrawingView createDrawingView( DrawingEditor editor ) {
    DrawingView view = 
      new MapView(editor, game);
    return view;
  }

  public Drawing createDrawing( DrawingEditor editor ) {
    return new StandardDrawing();
  }

  public JTextField createStatusField( DrawingEditor editor ) {
    return null;
  }
}

// A test stub implementation just to force some graphical updates.
class CityStub implements City{
  boolean redOwns = true;
  // a testing method just to make some
  // state changes
  public void  makeAChange() {
    redOwns = ! redOwns;
  }
  public Player getOwner() {
    return (redOwns ? Player.RED : Player.BLUE);
  }
  public int getSize() {
    return (redOwns ? 4 : 9);
  }
  public int getTreasury() {
    return 0;
  }
  public String getProduction() {
    return null;
  }

  @Override
  public void increment_round(GameImpl g) {

  }

  public String getWorkforceFocus() {
    return null;
  }

  @Override
  public int getProductionRate() {
    return 0;
  }

  @Override
  public int getGrowthRate() {
    return 0;
  }
}
