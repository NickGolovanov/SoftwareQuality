package nhl.stenden.factorymethod;

import nhl.stenden.observer.Presentation;

import java.io.IOException;

/**
 * Abstract base class for file accessors.
 * 
 * SOLID Principles Implementation:
 * - Single Responsibility Principle (SRP): Each accessor handles only file operations for a specific format
 * - Open-Closed Principle (OCP): New accessor types can be added by extending this class
 * - Liskov Substitution Principle (LSP): All concrete accessors must properly implement the abstract methods
 * - Dependency Inversion Principle (DIP): High-level modules depend on this abstraction
 */
public abstract class Accessor
{
    public static final String DEMO_NAME = "Demonstration presentation";
    public static final String DEFAULT_EXTENSION = ".xml";

    public Accessor()
    {
    }

    public abstract void loadFile(Presentation presentation, String filename) throws IOException;

    public abstract void saveFile(Presentation presentation, String filename) throws IOException;

}
