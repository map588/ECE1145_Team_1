package hotciv.visual;


import hotciv.framework.*;

import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
    CityStub city = new CityStub();

    CityFigure cf = new CityFigure( city,
            new Point( GfxConstants.getXFromColumn(4),
                    GfxConstants.getYFromRow(7) ) );
    editor.drawing().add(cf);
    editor.setTool( new ChangeCityTool(city, cf,game) );

  }
}

class ChangeCityTool extends NullTool {
  private City city;
  private CityFigure cityFigure;
  private Game game;

  private DrawingEditor editor;
  final private Position p = new Position(4,7);
  public ChangeCityTool(CityStub c, CityFigure cf, Game g) {
    city = c;
    cityFigure = cf;
    game = g;
  }

  protected boolean toggle = false;
  public void mouseDown(MouseEvent e, int x, int y) {

    if(GfxConstants.getPositionFromXY(x, y).equals(p)) {
      if(toggle) {
        game.changeProductionInCityAt(p, GameConstants.ARCHER);
        game.changeWorkForceFocusInCityAt(p, "production");
        toggle = false;
      }
      else {
        game.changeProductionInCityAt(p, GameConstants.LEGION);
        game.changeWorkForceFocusInCityAt(p, "food");
        toggle = true;
      }

    }
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