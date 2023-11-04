package hotciv.helpers.winnerManagers;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.helper_Interfaces.winnerManager;
import hotciv.standard.GameImpl;

public class epsilonWinnerManager implements winnerManager{


    public Player getWinner(GameImpl game){
            int[] numAttacks = game.getNumberOfSuccessfulAttacks();
            for (int i = 0; i < game.getNumberOfPlayers(); i++) {
                if (numAttacks[i] >= 3) {
                    return Player.values()[i];
                }
            }
            return null;
    }


}
