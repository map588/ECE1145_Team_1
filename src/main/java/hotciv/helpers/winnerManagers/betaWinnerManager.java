package hotciv.helpers.winnerManagers;

import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.helper_Interfaces.winnerManager;

public class betaWinnerManager implements winnerManager {

    public Player betaCivWinner(City[][] cities) {
        Position city1 = new Position(1, 1);
        Position city2 = new Position(1, 4);
        if (!g.returnWinnerFound() && (g.getCityAt(city1).getOwner() == g.getCityAt(city2).getOwner())) {
            return g.getCityAt(city1).getOwner();
        }
    }

}
