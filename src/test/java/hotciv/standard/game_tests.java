package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static hotciv.framework.GameConstants.WINNER_FOUND;
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
public class game_tests {
  private GameImpl game; //Changed Game to GameImpl -TPD

  /** Fixture for alphaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl();
  }

  // FRS p. 455 states that 'Red is the first player to take a turn'.
  @Test
  public void shouldBeRedAsStartingPlayer() {
    assertThat(game, is(notNullValue()));
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void shouldBe2Players() {
    assertThat(game.numberOfPlayers, is(2));
  }

  @Test
  public void player2IsBlue() {
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.BLUE));
  }

  @Test
  public void gameStartIs4000BC() {
      assertThat(game.getAge(), is(-4000));
  }

  @Test
  public void alphaCiv_Progress100YearsEveryTurn() {
    int turn1 = game.getAge();
    game.endOfTurn();
    int turn2 = game.getAge();

    assertThat((turn2 - turn1), is(100));
  }

  @Test
  public void betaCiv_DynamicWorldAging() {

    // 100 Years per turn pre- 100BC
    for (int i = 0; i < 38; i++){
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
    for (int j = 0; j < 33; j++) {
      game.endOfTurn();
    }
    assertThat(game.getAge(), is(1750));

    // 25 years per turn until 1900
    for (int h = 0; h < 5; h++) {
      game.endOfTurn();
    }
    assertThat(game.getAge(), is(1900));

    // 5 years per turn until 1970
    for (int k = 0; k < 34; k++) {
      game.endOfTurn();
    }
    assertThat(game.getAge(), is(1970));

    // 1 year per turn for the rest of the game
    for (int m = 0; m < 99; m++) {
      game.endOfTurn();
    }
    assertThat(game.getAge(), is(2070));
  }


  @Test
  public void alphaCiv_RedWinsIf3000BC() {
    for (int i = 0; i < 10; i++) {
      game.endOfTurn();
    }
    assertThat(game.getAge(), is(-3000));
    assertThat(game.getWinner(), is(Player.RED));
  }

  @Test
  public void betaCiv_FirstPlayerThatConquersWorldWins() {
    Position city1 = new Position(1,1);
    Position city2 = new Position(1,4);

    game.setCityAt(city1, Player.BLUE);

    assertThat(game.getWinner(), is(Player.BLUE)); // Blue owns both cities and should win

    game.setCityAt(city1, Player.RED);
    game.setCityAt(city2, Player.RED);

    assertThat(game.getWinner(), is(Player.BLUE)); // Blue already won the game, so they remain the winner.
  }

  @Test //TPD
  public void attackingUnitAlwaysWins(){
    Position posArcher = new Position(0, 2); //Attacker
    Position posLegion = new Position(2,3); //Defender
    assertThat(game.battle(posArcher, posLegion).getTypeString(), is("archer"));
  }

}
