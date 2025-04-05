package nhl.stenden.observer;

import java.util.List;

/**
 * Observable interface for implementing the Observer pattern.
 * 
 * SOLID Principles Implementation:
 * - Single Responsibility Principle (SRP): Handles only the observable aspect of objects
 * - Open-Closed Principle (OCP): New observable implementations can be added without modification
 * - Dependency Inversion Principle (DIP): Observers depend on this abstraction
 * - Interface Segregation Principle (ISP): Defines minimal interface needed for observable behavior
 */
public interface Observable
{
    void subscribe(Observer observer);

    void unsubscribe(Observer observer);

    void notifyObservers();
    
    List<Observer> getObservers();
}
