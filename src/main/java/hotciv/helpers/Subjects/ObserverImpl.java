package hotciv.helpers.Subjects;

import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;

public class ObserverImpl implements GameObserver {
    /**
     * invoked every time some change occurs on a position
     * in the world - a unit disappears or appears, a
     * city appears, a city changes player color, or any
     * other event that requires the GUI to redraw the
     * graphics on a particular position.
     */
    public void worldChangedAt() {
        worldChangedAt(null);
        String text = "Observer: World changed.";
        System.out.println(text);
    }

    /** invoked every time some change occurs on a position
     * in the world - a unit disappears or appears, a
     * city appears, a city changes player color, or any
     * other event that requires the GUI to redraw the
     * graphics on a particular position.
     * @param pos the position in the world that has changed state
     */
    @Override
    public void worldChangedAt(Position pos) {
        String text = String.format("Observer: World has been changed at position %d, %d.", pos.getRow(), pos.getColumn());
        System.out.println(text);
    }

    /** invoked just after the game's end of turn is called
     * to signal the new "player in turn" and world age state.
     * @param nextPlayer the next player that may move units etc.
     * @param age the present age of the world
     */
    @Override
    public void turnEnds(Player nextPlayer, int age){
        String text = String.format("Observer: Turn has ended. World age is %d, next player is %s.", age, nextPlayer.toString());
        System.out.println(text);
    }

    /** invoked whenever the user changes focus to another
     * tile (for inspecting the tile's unit and city
     * properties.)
     * @param position the position of the tile that is
     * now inspected/has focus.
     */
    @Override
    public void tileFocusChangedAt(Position position){
        String text = String.format("Observer: Tile focus changed to %d, %d.", position.getRow(), position.getColumn());
        worldChangedAt(position);
        System.out.println(text);
    }

    public void cityProductionChangedAt(Position position) {
        String text = String.format("Observer: City Production changed at %d, %d.", position.getRow(), position.getColumn());
        worldChangedAt(position);;
        System.out.println(text);
    }

    public void workForceFocusChangedAt(Position position) {
        String text = String.format("Observer: City work Focus changed at %d, %d.", position.getRow(), position.getColumn());
        worldChangedAt(position);
        System.out.println(text);
    }




}
