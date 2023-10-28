package hotciv.helper_Interfaces;
import static hotciv.framework.GameConstants.*;
import hotciv.framework.Position;
import hotciv.standard.*;

public interface roundManager {


        default public void incrementRound(GameImpl g) {
            Position p;
            for (int i = 0; i < WORLDSIZE; i++) {
                for (int j = 0; j < WORLDSIZE; j++) {
                    p = new Position(i, j);
                    if (g.getCityAt(p) != null) {
                        g.getCityAt(p).increment_round(g);
                    }
                }
            }
        }

}
