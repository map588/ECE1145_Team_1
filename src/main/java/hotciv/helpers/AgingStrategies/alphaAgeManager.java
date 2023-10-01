package hotciv.helpers.AgingStrategies;

import hotciv.helper_Interfaces.ageManager;
import hotciv.framework.Game;

public class alphaAgeManager implements ageManager {

        public alphaAgeManager(Game g){

        }

        public int incrementAge(int currentAge){
            return currentAge += 100;
        }

}
