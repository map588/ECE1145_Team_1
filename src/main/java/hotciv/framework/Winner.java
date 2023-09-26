package hotciv.framework;

import hotciv.framework.Player;
import hotciv.framework.Game;
import hotciv.framework.GameType;
import hotciv.framework.Position;

public class Winner {

    private static Player first_place = null;



    Game game;




    public Winner(Game g) {
        game = g;

        if (g.getRules() == GameType.betaCiv)
            betaCivWinner(g);
        else
            defaultWinner(g);
    }


    private void betaCivWinner(Game g) {
        Position city1 = new Position(1, 1);
        Position city2 = new Position(1, 4);
        if (!g.returnWinnerFound() && (g.getCityAt(city1).getOwner() == g.getCityAt(city2).getOwner())) {
            first_place = g.getCityAt(city1).getOwner();
            g.setWinnerFound(true);
        }
    }

    private void defaultWinner(Game g) {
        if (g.getAge() >= -3000)
            first_place = Player.RED;
    }

    public static Player returnWinner() {
        return first_place;
    }

}
