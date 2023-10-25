package hotciv.helpers.winnerManagers;

import hotciv.framework.Player;
import hotciv.helper_Interfaces.winnerManager;
import hotciv.standard.GameImpl;

public class epsilonWinnerManager implements winnerManager{

    public Player getWinner(GameImpl game){
        return Player.RED;
    }
}
