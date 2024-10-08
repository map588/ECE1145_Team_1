package hotciv.view;

import hotciv.framework.*;

import java.awt.*;
import java.util.*;
import java.util.List;

//import jdk.internal.event.Event;
import minidraw.framework.*;
import minidraw.standard.*;

import static hotciv.view.GfxConstants.*;

/** CivDrawing is a specialized Drawing (model component) from
 * MiniDraw that dynamically builds the list of Figures for MiniDraw
 * to render the Unit and other information objects that are visible
 * in the Game instance.
 *
 * TODO: This is a TEMPLATE for the SWEA Exercise! This means
 * that it is INCOMPLETE and that there are several options
 * for CLEANING UP THE CODE when you add features to it!

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

public class CivDrawing 
  implements Drawing, GameObserver {

  protected Drawing delegate;

  /**
   * store all moveable figures visible in this drawing = units
   */
  protected Map<Unit, UnitFigure> unitFigureMap;
  protected Map<Position, Unit> unitPositionMap;
  protected Map<City, CityFigure> cityFigureMap;
  protected Map<City, TextFigure> cityTextFigureMap;
  protected Map<Position, City> cityPositionMap;
  protected ImageFigure turnShieldIcon;
  protected Position currentFocusedTile;

  /**
   * the Game instance that this CivDrawing is going to render units
   * from
   */
  protected Game game;

  public CivDrawing(DrawingEditor editor, Game game) {
    super();
    this.delegate = new StandardDrawing();
    this.game = game;
    this.unitFigureMap = new HashMap<>();
    this.unitPositionMap = new HashMap<>();
    this.cityFigureMap = new HashMap<>();
    this.cityPositionMap = new HashMap<>();

    // register this unit drawing as listener to any game state
    // changes...
    game.addObserver(this);
    // ... and build up the set of figures associated with
    // units in the game.
    defineUnitMap();
    defineCityMap();
    // and the set of 'icons' in the status panel
    defineIcons();
  }

  /**
   * The CivDrawing should not allow client side
   * units to add and manipulate figures; only figures
   * that renders game objects are relevant, and these
   * should be handled by observer events from the game
   * instance. Thus this method is 'killed'.
   */
  public Figure add(Figure arg0) {
    throw new RuntimeException("Should not be used...");
  }


  /**
   * erase the old list of units, and build a completely new
   * one from scratch by iterating over the game world and add
   * Figure instances for each unit in the world.
   */
  protected void defineUnitMap() {
    // ensure no units of the old list are accidental in
    // the selection!
    clearSelection();

    // remove all unit figures in this drawing
    removeAllUnitFigures();

    // iterate world, and create a unit figure for
    // each unit in the game world, as well as
    // create an association between the unit and
    // the unitFigure in 'unitFigureMap'.
    Position p;
    for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
      for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
        p = new Position(r, c);
        Unit unit = game.getUnitAt(p);
        if (unit != null) {
          String type = unit.getTypeString();
          // convert the unit's Position to (x,y) coordinates
          Point point = new Point(getXFromColumn(p.getColumn()),
                  getYFromRow(p.getRow()));
          UnitFigure unitFigure =
                  new UnitFigure(type, point, unit);
          unitFigure.addFigureChangeListener(this);
          unitFigureMap.put(unit, unitFigure);
          unitPositionMap.put(p, unit);

          // also insert in delegate list as it is
          // this list that is iterated by the
          // graphics rendering algorithms
          delegate.add(unitFigure);
        }
      }
    }
  }

  /**
   * remove all unit figures in this
   * drawing, and reset the map (unit->unitfigure).
   * It is important to actually remove the figures
   * as it forces a graphical redraw of the screen
   * where the unit figure was.
   */
  protected void removeAllUnitFigures() {
    for (Unit u : unitFigureMap.keySet()) {
      UnitFigure uf = unitFigureMap.get(u);
      delegate.remove(uf);
    }
    unitFigureMap.clear();
    unitPositionMap.clear();
  }

   void defineCityMap() {

    removeCityFigures();

    Position p;

    for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
      for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
        p = new Position(r, c);
        City city = game.getCityAt(p);
        if (city != null) {
          Point point = new Point(getXFromColumn(p.getColumn()),
                  getYFromRow(p.getRow()));
          CityFigure cityFigure =
                  new CityFigure(city, point);
          cityFigure.addFigureChangeListener(this);
          cityFigureMap.put(city, cityFigure);
          cityPositionMap.put(p, city);
          delegate.add(cityFigure);
        }
      }
    }
  }

   void removeCityFigures() {
    for (City c : cityFigureMap.keySet()) {
      CityFigure cf = cityFigureMap.get(c);
      delegate.remove(cf);
    }
    cityFigureMap.clear();
    cityPositionMap.clear();
  }


   void defineIcons() {
    // TODO: Further development to include rest of figures needed

    //Shield icon

      Player p = game.getPlayerInTurn();
      ImageFigure shield;

      switch (p) {
        case RED:
          shield = new ImageFigure(RED_SHIELD,
                  new Point(TURN_SHIELD_X,
                          TURN_SHIELD_Y));
          break;
        case BLUE:
          shield = new ImageFigure(BLUE_SHIELD,
                  new Point(TURN_SHIELD_X,
                          TURN_SHIELD_Y));
          break;
        default:
          shield = new ImageFigure(NOTHING,
                  new Point(TURN_SHIELD_X,
                          TURN_SHIELD_Y));

      }

      turnShieldIcon = shield;
      // insert in delegate figure list to ensure graphical
      // rendering.
      //turnShieldIcon
      delegate.add(turnShieldIcon);


  }

  // === Observer Methods ===

  public void worldChangedAt(Position pos) {
    // TODO: Remove system.out debugging output
    System.out.println("CivDrawing: world changes at " + pos);
    // this is a really brute-force algorithm: destroy
    // all known units and build up the entire set again
    defineUnitMap();
    defineCityMap();
    defineIcons();

    Unit gameUnit = game.getUnitAt(pos);
    Unit mapUnit = unitPositionMap.get(pos);



    boolean new_unit_exits = (gameUnit != null);
    boolean old_unit_exits = (mapUnit != null);

    if (!new_unit_exits && old_unit_exits) {
      delegate.remove(unitFigureMap.get(mapUnit));
      unitFigureMap.remove(mapUnit);
      unitPositionMap.remove(pos);
    } else{

      mapUnit = gameUnit;

      delegate.remove(unitFigureMap.get(mapUnit));
      unitFigureMap.remove(mapUnit);
      unitPositionMap.remove(pos);

      Point point = new Point(getXFromColumn(pos.getColumn()), getYFromRow(pos.getRow()));

      assert gameUnit != null;
      UnitFigure unitFigure = new UnitFigure(gameUnit.getTypeString(), point, gameUnit);
      unitFigure.addFigureChangeListener(this);


      unitFigureMap.put(gameUnit, unitFigure);
      unitPositionMap.put(pos, gameUnit);
      delegate.add(unitFigure);
    }

    City gameCity = game.getCityAt(pos);
    City mapCity = cityPositionMap.get(pos);

    boolean new_city_exits = (gameCity != null);
    boolean old_city_exits = (mapCity != null);

      assert mapCity != null;
      String mapCityProduction = mapCity.getProduction();
      assert gameCity != null;
      String gameCityProduction = gameCity.getProduction();

    Player mapCityOwner  = mapCity.getOwner();
    Player gameCityOwner = gameCity.getOwner();



    if (!new_city_exits && old_city_exits) {
      delegate.remove(cityFigureMap.get(mapCity));
      cityFigureMap.remove(mapCity);
      cityPositionMap.remove(pos);
    } else if (new_city_exits && !old_city_exits) {
      Point point = new Point(getXFromColumn(pos.getColumn()),
              getYFromRow(pos.getRow()));

      CityFigure cityFigure = new CityFigure(gameCity, point);
      cityFigure.addFigureChangeListener(this);

      cityFigureMap.remove(mapCity);
      cityPositionMap.remove(pos);
      delegate.remove(cityFigureMap.get(mapCity));

      cityFigureMap.put(gameCity, cityFigure);
      cityPositionMap.put(pos, gameCity);
      delegate.add(cityFigure);
    }
    if (new_city_exits && old_city_exits) {
      mapCityProduction = mapCity.getProduction();
      gameCityProduction = gameCity.getProduction();
      mapCityOwner = mapCity.getOwner();
      gameCityOwner = gameCity.getOwner();
    }

    Point pt1 = new Point(CITY_PRODUCTION_X, CITY_PRODUCTION_Y);
    TextFigure cityProductionText = new TextFigure(gameCityProduction, pt1);
    cityTextFigureMap.remove(mapCity);
    cityTextFigureMap.put(gameCity, cityProductionText);

    Point pt2 = new Point(CITY_SHIELD_X, CITY_SHIELD_Y);
    assert gameCityOwner != null;
    TextFigure cityOwnerText = new TextFigure(gameCityOwner.toString(), pt2);

    cityTextFigureMap.remove(mapCity);
    cityTextFigureMap.put(gameCity, cityOwnerText);
    delegate.add(cityProductionText);



  }



  public void turnEnds(Player nextPlayer, int age) {


    // TODO: Remove system.out debugging output
    System.out.println( "CivDrawing: turnEnds at "+age+", next is "+nextPlayer );
    String playername = "red";
    if ( nextPlayer == Player.BLUE ) { playername = "blue"; }
    turnShieldIcon.set( playername+"shield",
                        new Point( TURN_SHIELD_X,
                                   TURN_SHIELD_Y ) );

    // TODO: Age output pending

  }

  public void tileFocusChangedAt(Position position) {

    System.out.println( "Fake it: tileFocusChangedAt "+position );
  }



  @Override
  public void requestUpdate() {
    // A request has been issued to repaint
    // everything. We simply rebuild the
    // entire Drawing.
    defineUnitMap();
    defineIcons();
    // TODO: Cities pending
  }

  public Position getCurrentFocusedTile() {
    return currentFocusedTile;
  }

  @Override
  public void addToSelection(Figure arg0) {
    delegate.addToSelection(arg0);
  }

  @Override
  public void clearSelection() {
    delegate.clearSelection();
  }

  @Override
  public void removeFromSelection(Figure arg0) {
    delegate.removeFromSelection(arg0);
  }

  @Override
  public List<Figure> selection() {
    return delegate.selection();
  }

  @Override
  public void toggleSelection(Figure arg0) {
    delegate.toggleSelection(arg0);
  }

  @Override
  public void figureChanged(FigureChangeEvent arg0) {
    delegate.figureChanged(arg0);
  }

  @Override
  public void figureInvalidated(FigureChangeEvent arg0) {
    delegate.figureInvalidated(arg0);
  }

  @Override
  public void figureRemoved(FigureChangeEvent arg0) {
    delegate.figureRemoved(arg0);
  }

  @Override
  public void figureRequestRemove(FigureChangeEvent arg0) {
    delegate.figureRequestRemove(arg0);
  }

  @Override
  public void figureRequestUpdate(FigureChangeEvent arg0) {
    delegate.figureRequestUpdate(arg0);
  }

  @Override
  public void addDrawingChangeListener(DrawingChangeListener arg0) {
    delegate.addDrawingChangeListener(arg0);   
  }

  @Override
  public void removeDrawingChangeListener(DrawingChangeListener arg0) {
    delegate.removeDrawingChangeListener(arg0);
  }

  @Override
  public Figure findFigure(int arg0, int arg1) {
    return delegate.findFigure(arg0, arg1);
  }

  @Override
  public Iterator<Figure> iterator() {
    return delegate.iterator();
  }

  @Override
  public void lock() {
    delegate.lock();
  }

  @Override
  public Figure remove(Figure arg0) {
    return delegate.remove(arg0);
  }

  @Override
  public void unlock() {
    delegate.unlock();
  }
}
