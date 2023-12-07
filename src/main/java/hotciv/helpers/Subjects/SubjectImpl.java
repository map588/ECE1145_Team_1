package hotciv.helpers.Subjects;

import hotciv.framework.Subject;
import hotciv.framework.GameObserver;
import java.util.ArrayList;
import java.util.List;
public class SubjectImpl implements Subject {

    private List<GameObserver> observerList = new ArrayList<>();
    @Override
    public void addObserver(GameObserver o) {
        observerList.add(o);
    }

    @Override
    public void removeObserver(GameObserver o) {
        observerList.remove(o);
    }

    @Override
    public void notifyObservers() {

    }
}
