package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Game;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


public class city_tests {
  private Game game;

  /** Fixture for alphaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl();
  }


  @Test
  public void redAndBlueCities() {
    Position posRED = new Position(1,1);    // location 1 for red city
    Position posBLUE = new Position(1,4);   // location 2 for blue city

    assertThat(game.getCityAt(posRED).getOwner(), is(Player.RED));
    assertThat(game.getCityAt(posBLUE).getOwner(), is(Player.BLUE));
  }

  @Test
  public void cityPopulationIsOne() {;
    assertThat(game, is(notNullValue()));
    Position pos = new Position(1,1);
    assertThat(game.getCityAt(pos).getSize(), is(1));
  }
}
