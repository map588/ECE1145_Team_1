package hotciv.helper_Interfaces;

import hotciv.standard.GameImpl;

public interface Observer {
    void update(GameImpl g);

    void setObserverEnabled(boolean observerEnabled);
}
