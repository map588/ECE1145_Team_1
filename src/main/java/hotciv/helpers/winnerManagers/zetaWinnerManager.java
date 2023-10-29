package hotciv.helpers.winnerManagers;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.helper_Interfaces.winnerManager;
import hotciv.standard.GameImpl;

public class zetaWinnerManager implements winnerManager{
    @Override
    public Player getWinner(GameImpl game) {
        if (game.getRoundNumber() <= 20) {
            Position city1 = new Position(1, 1);
            Position city2 = new Position(1, 4);
            if ((game.getCityAt(city1).getOwner() == game.getCityAt(city2).getOwner())) {
                return game.getCityAt(city1).getOwner();
            }
            return null;
        }

        else{
            int[] numAttacks = game.getNumberOfSuccessfulAttacks();
            for (int i = 0; i < game.getNumberOfPlayers(); i++){
                if (numAttacks[i] >= 3){
                    return Player.values()[i];
                }
            }
        }
        return null;
    }
}

