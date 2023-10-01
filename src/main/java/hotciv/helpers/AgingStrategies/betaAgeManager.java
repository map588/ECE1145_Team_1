package hotciv.helpers.AgingStrategies;

import hotciv.helper_Interfaces.ageManager;

public class betaAgeManager implements ageManager{

    public int incrementAge(int currentAge){
        return currentAge += 100;
    }

}
