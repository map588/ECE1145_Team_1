package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Position;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
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
public class betaCiv_tests {
  private GameImpl game; //Changed Game to GameImpl -TPD

  /**
   * Fixture for alphaCiv testing.
   */
  @Before
  public void setUp() {
    game = new GameImpl(GameType.betaCiv);
  }

  // FRS p. 455 states that 'Red is the first player to take a turn'.

  @Test
  public void betaCiv_DynamicWorldAging() {

    // 100 Years per turn pre- 100BC
    for (int i = 0; i < 39; i++) {
      game.endOfTurn();
    }
    assertThat(game.getAge(), is(-100));

    // Next turn is 1BC
    game.endOfTurn();
    assertThat(game.getAge(), is(-1));

    // Next turn is 1AD
    game.endOfTurn();
    assertThat(game.getAge(), is(1));

    // Next turn is 50AD
    game.endOfTurn();
    assertThat(game.getAge(), is(50));


    // 50 years per turn until 1750
    for (int j = 0; j < 34; j++) {
      game.endOfTurn();
    }
    assertThat(game.getAge(), is(1750));

    // 25 years per turn until 1900
    for (int h = 0; h < 6; h++) {
      game.endOfTurn();
    }
    assertThat(game.getAge(), is(1900));

    // 5 years per turn until 1970
    for (int k = 0; k < 14; k++) {
      game.endOfTurn();
    }
    assertThat(game.getAge(), is(1970));

    // 1 year per turn for the rest of the game
    for (int m = 0; m < 100; m++) {
      game.endOfTurn();
    }
    assertThat(game.getAge(), is(2070));
  }


  @Test
  public void betaCiv_FirstPlayerThatConquersWorldWins() {
    Position city1 = new Position(1, 1);
    Position city2 = new Position(1, 4);

    game.setCityAt(city1, Player.BLUE);

    assertThat(game.getCityAt(city1).getOwner(), is(Player.BLUE));

    assertThat(game.getWinner(), is(Player.BLUE)); // Blue owns both cities and should win

    game.setCityAt(city1, Player.RED);
    game.setCityAt(city2, Player.RED);

    assertThat(game.getWinner(), is(Player.BLUE)); // Blue already won the game, so they remain the winner.
  }
}

