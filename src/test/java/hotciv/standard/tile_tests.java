package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
public class tile_tests {
  private Game game;

  /** Fixture for alphaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl();
  }

    @Test
  public void correctStartingTileTypes() {
    assertThat(game, is(notNullValue()));


    for (int i = 0; i < WORLDSIZE; i++) {
      for (int j = 0; j < WORLDSIZE; j++) {
        Position pos = new Position(i,j);

        if (i == 0 && j == 1) {
          assertThat(game.getTileAt(pos).getTypeString(), is(HILLS));       // (0,1) is hills
        }
        else if (i == 1 && j == 0) {
          assertThat(game.getTileAt(pos).getTypeString(), is(OCEANS));      // (1,0) is ocean
        }
        else if (i == 2 && j == 2) {
          assertThat(game.getTileAt(pos).getTypeString(), is(MOUNTAINS));   // (2,2) is mountains
        }
        else {
          assertThat(game.getTileAt(pos).getTypeString(), is(PLAINS));      // all other tiles are plains
        }
      }
    }
   }
}
