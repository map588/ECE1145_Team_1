package hotciv.helpers.ageManagers;

import hotciv.framework.Game;
import hotciv.standard.GameImpl;
import hotciv.helper_Interfaces.ageManager;

public class betaAgeManager implements ageManager{


@Override
    public void incrementAge(GameImpl g){
        int current = g.getAge();
        // Refer to textbook for description of aging algorithm

        if (current < -100) {
            current += 100;
        }
        else if (current == -100) {
            current = -1;
        }
        else if (current == -1) {
            current = 1;
        }
        else if (current == 1) {
            current = 50;
        }
        else if (current < 1750) {
            current +=50;
        }
        else if (current < 1900) {
            current +=25;
        }
        else if (current < 1970) {
            current +=5;
        }
        else {
            current += 1;
        }

        g.setAge(current);
    }
}
