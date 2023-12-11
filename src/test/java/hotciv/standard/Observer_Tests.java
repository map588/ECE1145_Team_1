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
        game.moveUnit(new Position(0,2), new Position(0, 3));
        assertThat(toOutput.toString(), is("Observer: World has been changed at position 2, 0.\r\nObserver: World has been changed at position 3, 0.\r\n"));
    }

    @Test
    public void OBSturnEnds() {}
    @Test
    public void OBStileFocusChangedAt() {
        game.setTileFocus(new Position(1,1));
        assertThat(toOutput.toString(), is("Observer: Tile focus changed to 1, 1.\r\n"));
    }

/*
    @Test
    public void OBSproductionChangedAt() {
        City city1 =  game.getCityAt(new Position(1,1));

        (CityImpl) cit1.changeProductionInCity(ARCHER);
        assertThat(toOutput.toString(), is("Observer: City Production changed at 1, 1.\r\n"));
    }

    @Test
    public void OBSworkForceFocusChangedAt() {
        City city1 = game.getCityAt(new Position(1,1));
        city1.changeWorkForceFocusInCity("food");
        assertThat(toOutput.toString(), is("Observer: City work focus changed at %d, %d.\r\n"));
    }*/
}
