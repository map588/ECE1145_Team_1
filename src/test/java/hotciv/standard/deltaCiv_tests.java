package hotciv.standard;

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
    public void deltaCiv_StartingWorldLayout() {}
}
