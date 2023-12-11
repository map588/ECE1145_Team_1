package hotciv.standard;

import hotciv.helpers.Subjects.ObserverImpl;
import hotciv.manager_factories.alphaManagerFactory;
import hotciv.manager_factories.semiManagerFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import static hotciv.framework.GameConstants.*;
import static org.hamcrest.CoreMatchers.is;

import hotciv.framework.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Observer_Tests {

    private Game game;

    private final ByteArrayOutputStream toOutput = new ByteArrayOutputStream();
    private final PrintStream outputStream = System.out;


    @Before
    public void setUp() {
        game = new GameImpl(new alphaManagerFactory(), 2);
        game.addObserver(new ObserverImpl());

        System.setOut(new PrintStream(toOutput));
    }


    @Test
    public void OBSworldChangedAt() {
        game.setTileFocus(new Position(1,1));
        assertThat(toOutput.toString(), is("Observer: Tile focus changed to 1, 1."));
    }


    @Test
    public void OBSNullWorldChangedAt() {

    }


    @Test
    public void OBSturnEnds() {}

    @Test
    public void OBStileFocusChangedAt() {}



}
