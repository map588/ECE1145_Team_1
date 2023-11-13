package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.manager_factories.thetaManagerFactory;
import hotciv.manager_factories.alphaManagerFactory;
import org.junit.Before;
import org.junit.Test;

import static hotciv.framework.GameConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class moveUnit_Systematic {
    private GameImpl game;
    private GameImpl gameAlpha;

    /** Fixture for thetaciv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new thetaManagerFactory(), 2);
        gameAlpha = new GameImpl(new alphaManagerFactory(), 2);
    }

    //Theta tests
    @Test
    public void redArcherMovesToTileOccupiedByRedLegion() {
        Position archerPos = new Position(2, 0);
        Position legionPos = new Position(3, 0);
        game.createUnitAt(archerPos, ARCHER, Player.RED);
        game.createUnitAt(legionPos, LEGION, Player.RED);
        assertThat(game.moveUnit(archerPos, legionPos), is(false));
        assertThat(game.getUnitAt(legionPos).getTypeString(), is(LEGION));
        assertThat(game.getUnitAt(archerPos).getTypeString(), is(ARCHER));
    }

    @Test
    public void redArcherMovesToUnoccupiedTile() {
        Position archerPos = new Position(2, 0);
        Position newPos = new Position(3, 1);
        game.createUnitAt(archerPos, ARCHER, Player.RED);
        assertThat(game.moveUnit(archerPos, newPos), is(true));
        assertThat(game.getUnitAt(newPos).getTypeString(), is(ARCHER));
        assertThat(game.getUnitAt(archerPos), is(nullValue()));
    }

    @Test
    public void redUfoMovesToMountainTile() {
        Position ufoPos = new Position(2, 1);
        Position mountainPos = new Position(2, 2);
        game.createUnitAt(ufoPos, UFO, Player.RED);
        assertThat(game.getTileAt(mountainPos).getTypeString(), is(MOUNTAINS));
        assertThat(game.moveUnit(ufoPos, mountainPos), is(true));
        assertNull(game.getUnitAt(ufoPos));
        assertThat(game.getUnitAt(mountainPos).getTypeString(), is(UFO));
    }

    @Test
    public void redArcherMovesToMountainTile() {
        Position archerPos = new Position(2, 1);
        Position mountainPos = new Position(2, 2);
        game.createUnitAt(archerPos, ARCHER, Player.RED);
        assertThat(game.getTileAt(mountainPos).getTypeString(), is(MOUNTAINS));
        assertThat(game.moveUnit(archerPos, mountainPos), is(false));
        assertThat(game.getUnitAt(archerPos).getTypeString(), is(ARCHER));
        assertThat(game.getUnitAt(mountainPos), is(nullValue()));
    }

    @Test
    public void redArcherMovesToHillsTile() {
        Position archerPos = new Position(0, 0);
        Position hillsPos = new Position(0, 1);
        game.createUnitAt(archerPos, ARCHER, Player.RED);
        assertThat(game.getTileAt(hillsPos).getTypeString(), is(HILLS));
        assertThat(game.moveUnit(archerPos, hillsPos), is(true));
        assertThat(game.getUnitAt(hillsPos).getTypeString(), is(ARCHER));
        assertThat(game.getUnitAt(archerPos), is(nullValue()));
    }

    @Test
    public void redArcherMoves2SpacesToPlainsTile() {
        Position archerPos = new Position(2, 0);
        Position plainsPos = new Position(4, 0);
        game.createUnitAt(archerPos, ARCHER, Player.RED);
        assertThat(game.getTileAt(plainsPos).getTypeString(), is(PLAINS));
        assertThat(game.moveUnit(archerPos, plainsPos), is(false));
        assertThat(game.getUnitAt(archerPos).getTypeString(), is(ARCHER));
        assertThat(game.getUnitAt(plainsPos), is(nullValue()));
    }

    @Test
    public void redUfoMoves3Spaces() {
        Position ufoPos = new Position(2, 1);
        Position newPos = new Position(5, 1);
        game.createUnitAt(ufoPos, UFO, Player.RED);
        assertThat(game.moveUnit(ufoPos, newPos), is(false));
        assertThat(game.getUnitAt(ufoPos).getTypeString(), is(UFO));
        assertThat(game.getUnitAt(newPos), is(nullValue()));
    }

    @Test
    public void redArcherMoves1Space() {
        Position archerPos = new Position(2, 0);
        Position newPos = new Position(3, 0);
        game.createUnitAt(archerPos, ARCHER, Player.RED);
        assertThat(game.moveUnit(archerPos, newPos), is(true));
        assertThat(game.getUnitAt(newPos).getTypeString(), is(ARCHER));
        assertThat(game.getUnitAt(archerPos), is(nullValue()));
    }

    @Test
    public void redUfoMoves2Spaces() {
        Position ufoPos = new Position(2, 1);
        Position midPos = new Position(3, 1);
        Position newPos = new Position(4, 1);
        game.createUnitAt(ufoPos, UFO, Player.RED);
        assertThat(game.moveUnit(ufoPos, midPos), is(true));
        assertThat(game.moveUnit(midPos, newPos), is(true));
        assertThat(game.getUnitAt(newPos).getTypeString(), is(UFO));
        assertThat(game.getUnitAt(ufoPos), is(nullValue()));
        assertThat(game.getUnitAt(midPos), is(nullValue()));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void redArcherMovesOOBHighRow() {
        Position archerPos = new Position(15, 15);
        Position newPos = new Position(16, 15);
        game.createUnitAt(archerPos, ARCHER, Player.RED);
        assertThat(game.moveUnit(archerPos, newPos), is(false));
        assertThat(game.getUnitAt(archerPos).getTypeString(), is(ARCHER));
        assertThat(game.getUnitAt(newPos), is(nullValue()));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void redArchersMovesOOBLowRow() {
        Position archerPos = new Position(0, 0);
        Position newPos = new Position(-1, 0);
        game.createUnitAt(archerPos, ARCHER, Player.RED);
        assertThat(game.moveUnit(archerPos, newPos), is(false));
        assertThat(game.getUnitAt(archerPos).getTypeString(), is(ARCHER));
        assertThat(game.getUnitAt(newPos), is(nullValue()));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void redArcherMovesOOBHighColumn() {
        Position archerPos = new Position(15, 15);
        Position newPos = new Position(15, 16);
        game.createUnitAt(archerPos, ARCHER, Player.RED);
        assertThat(game.moveUnit(archerPos, newPos), is(false));
        assertThat(game.getUnitAt(archerPos).getTypeString(), is(ARCHER));
        assertThat(game.getUnitAt(newPos), is(nullValue()));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void redArcherMovesOOBLowColumn() {
        Position archerPos = new Position(0, 0);
        Position newPos = new Position(0, -1);
        game.createUnitAt(archerPos, ARCHER, Player.RED);
        assertThat(game.moveUnit(archerPos, newPos), is(false));
        assertThat(game.getUnitAt(archerPos).getTypeString(), is(ARCHER));
        assertThat(game.getUnitAt(newPos), is(nullValue()));
    }

    @Test
    public void redArcherMoves1RowTile() {
        Position archerPos = new Position(2, 0);
        Position newPos = new Position(3, 0);
        game.createUnitAt(archerPos, ARCHER, Player.RED);
        assertThat(game.moveUnit(archerPos, newPos), is(true));
        assertThat(game.getUnitAt(newPos).getTypeString(), is(ARCHER));
        assertThat(game.getUnitAt(archerPos), is(nullValue()));
    }

    @Test
    public void redArcherMoves1ColumnTile() {
        Position archerPos = new Position(2, 0);
        Position newPos = new Position(2, 1);
        game.createUnitAt(archerPos, ARCHER, Player.RED);
        assertThat(game.moveUnit(archerPos, newPos), is(true));
        assertThat(game.getUnitAt(newPos).getTypeString(), is(ARCHER));
        assertThat(game.getUnitAt(archerPos), is(nullValue()));
    }

    //A few spot checks for alpha, other civs
    @Test
    public void ALPHAredArcherMovesToTileOccupiedByRedLegion() {
        Position archerPos = new Position(2, 0);
        Position legionPos = new Position(3, 0);
        gameAlpha.createUnitAt(archerPos, ARCHER, Player.RED);
        gameAlpha.createUnitAt(legionPos, LEGION, Player.RED);
        assertThat(gameAlpha.moveUnit(archerPos, legionPos), is(false));
        assertThat(gameAlpha.getUnitAt(legionPos).getTypeString(), is(LEGION));
        assertThat(gameAlpha.getUnitAt(archerPos).getTypeString(), is(ARCHER));
    }

    @Test
    public void ALPHAredArcherMovesToUnoccupiedTile() {
        Position archerPos = new Position(2, 0);
        Position newPos = new Position(3, 1);
        gameAlpha.createUnitAt(archerPos, ARCHER, Player.RED);
        assertThat(gameAlpha.moveUnit(archerPos, newPos), is(true));
        assertThat(gameAlpha.getUnitAt(newPos).getTypeString(), is(ARCHER));
        assertThat(gameAlpha.getUnitAt(archerPos), is(nullValue()));
    }

    @Test
    public void ALPHAredArcherMovesToMountainTile() {
        Position archerPos = new Position(2, 1);
        Position mountainPos = new Position(2, 2);
        gameAlpha.createUnitAt(archerPos, ARCHER, Player.RED);
        assertThat(gameAlpha.getTileAt(mountainPos).getTypeString(), is(MOUNTAINS));
        assertThat(gameAlpha.moveUnit(archerPos, mountainPos), is(false));
        assertThat(gameAlpha.getUnitAt(archerPos).getTypeString(), is(ARCHER));
        assertThat(gameAlpha.getUnitAt(mountainPos), is(nullValue()));
    }

    @Test
    public void ALPHAredArcherMovesToHillsTile() {
        Position archerPos = new Position(0, 0);
        Position hillsPos = new Position(0, 1);
        gameAlpha.createUnitAt(archerPos, ARCHER, Player.RED);
        assertThat(gameAlpha.getTileAt(hillsPos).getTypeString(), is(HILLS));
        assertThat(gameAlpha.moveUnit(archerPos, hillsPos), is(true));
        assertThat(gameAlpha.getUnitAt(hillsPos).getTypeString(), is(ARCHER));
        assertThat(gameAlpha.getUnitAt(archerPos), is(nullValue()));
    }

    @Test
    public void ALPHAredArcherMoves2SpacesToPlainsTile() {
        Position archerPos = new Position(2, 0);
        Position plainsPos = new Position(4, 0);
        gameAlpha.createUnitAt(archerPos, ARCHER, Player.RED);
        assertThat(gameAlpha.getTileAt(plainsPos).getTypeString(), is(PLAINS));
        assertThat(gameAlpha.moveUnit(archerPos, plainsPos), is(false));
        assertThat(gameAlpha.getUnitAt(archerPos).getTypeString(), is(ARCHER));
        assertThat(gameAlpha.getUnitAt(plainsPos), is(nullValue()));
    }
}
