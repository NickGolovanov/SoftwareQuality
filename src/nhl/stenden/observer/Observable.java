package nhl.stenden.observer;

import java.util.List;

public interface Observable
{
    void subscribe(Observer observer);

    void unsubscribe(Observer observer);

    void notifyObservers();
    
    List<Observer> getObservers();
}
