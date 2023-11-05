package hotciv.helpers.winnerManagers;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.helper_Interfaces.winnerManager;
import hotciv.standard.GameImpl;

public class zetaWinnerManager implements winnerManager {

    @Override
    public Player getWinner(GameImpl game) {
        winnerManager subStrategy;

        if (game.getRoundNumber() < 20) {
            subStrategy = new betaWinnerManager();
        } else {
            subStrategy = new epsilonWinnerManager();
        }

        return subStrategy.getWinner(game);
    }
}

