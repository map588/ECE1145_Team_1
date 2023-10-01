package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Position;
import org.junit.Before;
import org.junit.Test;

import static hotciv.framework.GameConstants.ARCHER;
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
    game = new GameImpl(GameType.alphaCiv);
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
    game.endOfTurn();
    int turn2 = game.getAge();

    assertThat((turn2 - turn1), is(100));
  }

  @Test
  public void alphaCiv_RedWinsIf3000BC() {
    for (int i = 0; i < 10; i++) {
      game.endOfTurn();
    }
    assertThat(game.getAge(), is(-3000));
    assertThat(game.getWinner(), is(Player.RED));
  }

  @Test //TPD
  public void attackingUnitAlwaysWins(){
    Position posArcher = new Position(0, 2); //Attacker
    Position posLegion = new Position(2,3); //Defender
    assertThat(game.battle(posArcher, posLegion).getTypeString(), is(ARCHER));
  }

  @Test
  public void settlerDoesNothing(){
    Position posSettler = new Position(3, 4);
    assertThat(game.settlerAction(posSettler), is(0));
  }

  @Test
  public void archerDoesNothing(){
    Position posArcher = new Position(0,2);
    game.archerAction(posArcher);
    assertThat(game.getUnitAt(posArcher).getDefensiveStrength(), is(0));
    assertThat(game.getUnitAt(posArcher).getMoveCount(), is(1));
  }

}
