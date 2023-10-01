package hotciv.helper_Interfaces;

import hotciv.framework.Game;
import hotciv.framework.Player;

import hotciv.standard.GameImpl;
import hotciv.standard.World;


public interface winnerManager {

    default public Player getWinner(GameImpl game){

            if (game.getAge() >= -3000)
                return Player.RED;
            else
                return null;
        }
}

