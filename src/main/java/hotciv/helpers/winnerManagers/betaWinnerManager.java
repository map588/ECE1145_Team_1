package hotciv.helpers.winnerManagers;

import hotciv.framework.*;
import hotciv.helper_Interfaces.winnerManager;
import hotciv.standard.GameImpl;

public class betaWinnerManager implements winnerManager {

    public Player betaCivWinner(GameImpl game) {
        Position city1 = new Position(1, 1);
        Position city2 = new Position(1, 4);
        if ((game.world.getCityAt(city1).getOwner() == game.getCityAt(city2).getOwner())) {
            return game.world.getCityAt(city1).getOwner();
        }
        return null;
    }
}
