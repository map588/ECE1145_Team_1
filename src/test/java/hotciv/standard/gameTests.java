package hotciv.standard;

import hotciv.framework.GameType;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.manager_factories.alphaManagerFactory;
import org.junit.Before;
import org.junit.Test;

import static hotciv.framework.GameConstants.ARCHER;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
public class gameTests {
  private GameImpl game; //Changed Game to GameImpl -TPD

  /** Fixture for alphaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl(new alphaManagerFactory(), 2);
  }

  // FRS p. 455 states that 'Red is the first player to take a turn'.
  @Test
  public void shouldBeRedAsStartingPlayer() {
    assertThat(game, is(notNullValue()));
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void shouldBe2Players() {
    assertThat(game.getNumberOfPlayers(), is(2));
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
    for (int i = 0; i < game.getNumberOfPlayers(); i++) {game.endOfTurn();}
    int turn2 = game.getAge();

    assertThat((turn2 - turn1), is(100));
  }

  @Test
  public void alphaCiv_RedWinsIf3000BC() {
    for (int i = 0; i < (10 * game.getNumberOfPlayers()); i++) {
      game.endOfTurn();
    }
    assertThat(game.getAge(), is(-3000));
    assertThat(game.getWinner(), is(Player.RED));
  }

  @Test //TPD
  public void attackingUnitAlwaysWins(){
    Position posArcher = new Position(0, 2); //Attacker
    Position posLegion = new Position(2,3); //Defender
    assertTrue(game.attack(posArcher, posLegion));
  }

  @Test
  public void settlerDoesNothing(){
    Position posSettler = new Position(3, 4);
    game.performUnitActionAt(posSettler);
    assertThat(game.getCityAt(posSettler), is(nullValue()));
  }

  @Test
  public void archerDoesNothing(){
    Position posArcher = new Position(0,2);
    game.performUnitActionAt(posArcher);
    assertThat(game.getUnitAt(posArcher).getDefensiveStrength(), is(3));
    assertThat(game.getUnitAt(posArcher).getMoveCount(), is(1));
  }

}
