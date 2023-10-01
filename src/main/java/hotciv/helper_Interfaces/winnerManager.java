package hotciv.helper_Interfaces;

import hotciv.framework.*;


public interface winnerManager {

    default Player getWinner(Game g) {
        if (g.getAge() >= -3000)
            return Player.RED;
        else
            return null;
    }

}
