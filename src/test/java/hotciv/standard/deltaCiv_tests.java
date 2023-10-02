package hotciv.standard;

import static hotciv.framework.GameConstants.*;
import hotciv.framework.Position;
import hotciv.framework.Game;
import hotciv.framework.GameType;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class deltaCiv_tests {
    private Game game;


    @Before
    public void setUp() { game = new GameImpl(GameType.deltaCiv);}


    @Test
    public void deltaCiv_StartingWorldLayout() {
        // too many tiles to check all...

        Position tile1 = new Position(5, 14);
        Position tile2 = new Position(13,8);
        Position tile3 = new Position(5,0);
        Position tile4 = new Position(6,6);

        assertThat(game.getTileAt(tile1).getTypeString(), is(HILLS));
        assertThat(game.getTileAt(tile2).getTypeString(), is(FOREST));
        assertThat(game.getTileAt(tile3).getTypeString(), is(MOUNTAINS));
        assertThat(game.getTileAt(tile4).getTypeString(), is(OCEANS));
    }
}
