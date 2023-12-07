package hotciv.framework;

import hotciv.framework.GameObserver;

public interface Subject {
    public void addObserver(GameObserver o);
    public void removeObserver(GameObserver o);
    public void notifyObservers();

}
