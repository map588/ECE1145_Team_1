package hotciv.helpers.winnerManagers;

import hotciv.helper_Interfaces.winnerManager;
import hotciv.framework.Player;
import hotciv.framework.Game;

public class alphaWinnerManager implements winnerManager {

    public Player getWinner(Game game){
        if (game.getAge() >= -3000)
            return Player.RED;
        else
            return null;
    }

}
