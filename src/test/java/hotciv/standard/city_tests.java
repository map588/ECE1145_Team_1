package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Game;
import hotciv.framework.GameType;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


public class city_tests {
  private Game game;

  /**
   * Fixture for alphaciv testing.
   */
  @Before
  public void setUp() {
    game = new GameImpl(GameType.alphaCiv, 2);
  }


  @Test
  public void gameExists() {
    assertThat(game, is(notNullValue()));
  }


  @Test
  public void redAndBlueCities() {
    Position posRED = new Position(1, 1);    // location 1 for red city
    Position posBLUE = new Position(1, 4);   // location 2 for blue city

    assertThat(game.getCityAt(posRED).getOwner(), is(Player.RED));
    assertThat(game.getCityAt(posBLUE).getOwner(), is(Player.BLUE));
  }

  @Test
  public void cityPopulationIsOne() {
    ;
    Position posRED = new Position(1, 1);
    Position posBLUE = new Position(1, 4);

    assertThat(game.getCityAt(posRED).getSize(), is(1));
    assertThat(game.getCityAt(posBLUE).getSize(), is(1));
  }

  @Test
  public void cityTreasuryIsZero() {
    Position posRED = new Position(1, 1);
    Position posBLUE = new Position(1, 4);

    assertThat(game.getCityAt(posRED).getTreasury(), is(0));
    assertThat(game.getCityAt(posBLUE).getTreasury(), is(0));
  }


  @Test
  public void cityProducesSixPerRound() {
    Position posRED = new Position(1, 1);
    Position posBLUE = new Position(1, 4);

    assertThat(game.getCityAt(posRED).getTreasury(), is(0));
    assertThat(game.getCityAt(posBLUE).getTreasury(), is(0));

    game.endOfTurn();
    game.endOfTurn();

    assertThat(game.getCityAt(posRED).getTreasury(), is(6));
    assertThat(game.getCityAt(posBLUE).getTreasury(), is(6));
  }


  @Test
  public void cityHasPopulationOneAndDoesNotGrow() { //Current Requirements for city growth, will change later -MAP
    Position posRED = new Position(1, 1);
    Position posBLUE = new Position(1, 4);

    assertThat(game.getCityAt(posRED).getSize(), is(1));
    assertThat(game.getCityAt(posBLUE).getSize(), is(1));

    game.endOfTurn();
    game.endOfTurn();

    assertThat(game.getCityAt(posRED).getSize(), is(1));
    assertThat(game.getCityAt(posBLUE).getSize(), is(1));
  }


@Test
public void productionIncreasesByRate() { //Current Requirements for city growth, will change later -MAP
  Position posRED = new Position(1, 1);
  Position posBLUE = new Position(1, 4);

  int lastTreasuryRED = game.getCityAt(posRED).getTreasury();
  int lastTreasuryBLUE = game.getCityAt(posBLUE).getTreasury();

  game.endOfTurn();
  game.endOfTurn();

  int newTreasuryRED = game.getCityAt(posRED).getTreasury();
    int newTreasuryBLUE = game.getCityAt(posBLUE).getTreasury();

  assertThat((newTreasuryRED - lastTreasuryRED), is(game.getCityAt(posRED).getProductionRate()));
  assertThat(game.getCityAt(posBLUE).getSize(), is(1));
  }
}