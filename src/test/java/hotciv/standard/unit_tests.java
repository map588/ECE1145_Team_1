package hotciv.standard;


import hotciv.framework.Position;
import hotciv.framework.Game;
import org.junit.Before;
import org.junit.Test;

import static hotciv.framework.GameConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/** Skeleton class for AlphaCiv test cases

    Updated Oct 2015 for using Hamcrest matchers

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
public class unit_tests {
  private Game game;

  /**
   * Fixture for alphaciv testing.
   */
  @Before
  public void setUp() {
    game = new GameImpl(GameType.alphaCiv);
  }


  @Test
  public void startingUnits() {
    Position posArcher = new Position(0, 2);
    Position posSettler = new Position(3, 4);
    Position posLegion = new Position(2, 3);
    assertThat(game.getUnitAt(posArcher).getTypeString(), is(ARCHER));
    assertThat(game.getUnitAt(posSettler).getTypeString(), is(SETTLER));
    assertThat(game.getUnitAt(posLegion).getTypeString(), is(LEGION));
  }
    
    
  @Test
  public void oneUnitPerTile() {
    Position posArcher = new Position(0, 2);
    Position posSettler = new Position(3, 4);
    Position posLegion = new Position(2, 3);
    assertThat(game.getUnitAt(posArcher).getTypeString(), is(ARCHER));
    assertThat(game.getUnitAt(posSettler).getTypeString(), is(SETTLER));
    assertThat(game.getUnitAt(posLegion).getTypeString(), is(LEGION));

    boolean twoUnitsOnTile = game.moveUnit(posArcher, posLegion);
    boolean twoUnitsOnTile2 = game.moveUnit(posLegion, posArcher);
    boolean twoUnitsOnTile3 = game.moveUnit(posSettler, posSettler);

    boolean unitsPlacedOverEachOther = twoUnitsOnTile || twoUnitsOnTile2 || twoUnitsOnTile3;

    assertThat(unitsPlacedOverEachOther, is(false));
    
  }

} //end unit_test