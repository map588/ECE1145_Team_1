package hotciv.helper_Interfaces;

import hotciv.framework.Game;

public interface ageManager {

    default public int incrementAge(Game g){
        return g.getAge() + 100;
    }

}
