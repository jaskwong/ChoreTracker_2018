package Observer;

import Model.Chore;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<ChoreObserver> observers = new ArrayList<>();

    public void addChoreObserver(ChoreObserver choreObserver){
        if (!observers.contains(choreObserver)){
            observers.add(choreObserver);
        }
    }

    public void notifyObservers(Chore c){
        for (ChoreObserver observer: observers){
            observer.update(c);
        }
    }
}
