package hotciv.helper_Interfaces;
import  hotciv.framework.Game;
import hotciv.standard.GameImpl;

public interface ageManager {

    public final int START_AGE = -4000;

    default void incrementAge(GameImpl g){
        int newAge = g.getAge() + 100;

        g.setAge(newAge);
    }
}
