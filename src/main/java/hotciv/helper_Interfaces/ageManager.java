package hotciv.helper_Interfaces;
import  hotciv.framework.Game;
import hotciv.standard.GameImpl;

public interface ageManager {

    public final int START_AGE = -4000;

    default public int incrementAge(Game g){
        return g.getAge() + 100;
    }

}
