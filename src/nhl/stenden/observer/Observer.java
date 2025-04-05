package nhl.stenden.observer;

import nhl.stenden.Slide;

/**
 * Observer interface for implementing the Observer pattern.
 * 
 * SOLID Principles Implementation:
 * - Single Responsibility Principle (SRP): Each observer has a single purpose of handling updates
 * - Interface Segregation Principle (ISP): Interface defines only the essential method needed
 * - Open-Closed Principle (OCP): New observers can be added without modifying existing code
 * - Dependency Inversion Principle (DIP): High-level modules depend on this abstraction
 */
public interface Observer
{
    void update(Slide slide);
}
